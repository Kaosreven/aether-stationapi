package io.github.kaosreven.aether.blockentity.block;

import io.github.kaosreven.aether.achievement.AetherAchievements;
import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.entity.living.EntityMoa;
import io.github.kaosreven.aether.item.AetherItems;
import io.github.kaosreven.aether.util.MoaColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;

import static io.github.kaosreven.aether.block.AetherBlocks.MOD_ID;

public class BlockEntityIncubator extends BlockEntityAetherMachine implements Inventory {
    public BlockEntityIncubator() {
    }

    @Override
    public int size() {
        return this.ItemStacks.length-1;
    }

    @Override
    public int getCookProgressScaled(int i) {
        return this.Progress * i / 6000;
    }

    @Override
    public String getName() {
        return MOD_ID.id("Incubator").path;
    }

    @Override
    public void tick() {
        if (this.PowerRemaining > 0) {
            --this.PowerRemaining;
            if (this.getStack(1) != null) {
                ++this.Progress;
            }
        }

        if (this.ItemStacks[1] == null || this.ItemStacks[1].itemId != AetherItems.MoaEgg.id) {
            this.Progress = 0;
        }

        if (this.Progress >= 6000) {
            if (this.ItemStacks[1] != null) {
                EntityMoa moa = new EntityMoa(this.world, true, false, false, MoaColor.getColour(this.ItemStacks[1].getDamage()));
                moa.setPosition((double) this.x + 0.5, (double) this.y + 1.5, (double) this.z + 0.5);
                this.world.spawnEntity(moa);
            }

            PlayerEntity player = world.getClosestPlayer(x, y, z, 100);
            if (player != null)
                AetherAchievements.giveAchievement(AetherAchievements.incubator, player);
            this.removeStack(1, 1);
            this.Progress = 0;
        }

        if (this.PowerRemaining <= 0 && this.ItemStacks[1] != null && this.ItemStacks[1].itemId == AetherItems.MoaEgg.id && this.getStack(0) != null && this.getStack(0).itemId == AetherBlocks.AmbrosiumTorch.asItem().id) {
            this.PowerRemaining += 1000;
            this.removeStack(0, 1);
        }
    }
}
