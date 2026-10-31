package io.github.kaosreven.aether.item.tool;

import io.github.kaosreven.aether.item.AetherItems;
import com.periut.accessoryapi.api.TickableInArmorSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemPhoenixArmour extends ItemColouredArmour implements TickableInArmorSlot {
    public ItemPhoenixArmour(Identifier i, int j, String s, int l, int col) {
        super(i, j, s, l, col);
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity player, ItemStack itemInstance) {
        if (player.isWet()) {
            itemInstance.damage(1, player);
            if (itemInstance.getDamage2() > itemInstance.getMaxDamage() - 2) {
                return switch (equipmentSlot) {
                    case 0 -> new ItemStack(AetherItems.ObsidianHelm, 1);
                    case 1 -> new ItemStack(AetherItems.ObsidianBody, 1);
                    case 2 -> new ItemStack(AetherItems.ObsidianLegs, 1);
                    case 3 -> new ItemStack(AetherItems.ObsidianBoots, 1);
                    default -> null;
                };
            }
        }

        return itemInstance;
    }
}
