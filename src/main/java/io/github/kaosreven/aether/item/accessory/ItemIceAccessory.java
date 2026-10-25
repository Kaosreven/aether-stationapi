package io.github.kaosreven.aether.item.accessory;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemIceAccessory extends ItemMoreArmor {
    public ItemIceAccessory(Identifier i, int j, String path, int l, int m) {
        super(i, j, path, l, m);
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity player, ItemStack itemInstance) {
        super.tickWhileWorn(player, itemInstance);
        boolean froze = false;
        final int i = MathHelper.floor(player.x);
        final int j = MathHelper.floor(player.boundingBox.minY);
        final int k = MathHelper.floor(player.z);
        for (int l = i - 1; l <= i + 1; ++l) {
            for (int i2 = j - 1; i2 <= j + 1; ++i2) {
                for (int j2 = k - 1; j2 <= k + 1; ++j2) {
                    froze = switch (player.world.getBlockId(l, i2, j2)) {
                        case 8, 9 -> {
                            player.world.setBlock(l, i2, j2, Block.ICE.id);
                            yield true;
                        }
                        case 10, 11 -> {
                            player.world.setBlock(l, i2, j2, Block.OBSIDIAN.id);
                            yield true;
                        }
                        default -> froze;
                    };
                }
            }
        }

        if (froze) {
            itemInstance.damage(1, player);
            if (itemInstance.getDamage2() > itemInstance.getMaxDamage() - 2) {
                itemInstance = null;
            }
        }

        return itemInstance;
    }
}
