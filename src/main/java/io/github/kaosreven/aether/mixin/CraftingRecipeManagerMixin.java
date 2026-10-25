package io.github.kaosreven.aether.mixin;

import io.github.kaosreven.aether.block.AetherBlocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(CraftingRecipeManager.class)
public abstract class CraftingRecipeManagerMixin
{
	@SuppressWarnings("rawtypes")
	@Shadow
	private List recipes;

	@Inject(method = "craft", at = @At("HEAD"), cancellable = true)
	private void prioritizeAetherRecipes(CraftingInventory craftingInventory, CallbackInfoReturnable<ItemStack> cir)
	{
		for (int i = 0; i < this.recipes.size(); i++)
		{
			CraftingRecipe recipe = (CraftingRecipe) this.recipes.get(i);
			if (recipe.matches(craftingInventory))
			{
				ItemStack output = recipe.getOutput();

				if (output != null && isAetherItem(output))
				{
					cir.setReturnValue(recipe.craft(craftingInventory));
					return;
				}
			}
		}
	}

	@Unique
    private static boolean isAetherItem(ItemStack stack)
	{
		return stack.getItem().getTranslationKey().contains(AetherBlocks.MOD_ID.toString());
	}
}