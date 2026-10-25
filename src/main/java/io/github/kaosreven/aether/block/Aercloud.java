package io.github.kaosreven.aether.block;

import io.github.kaosreven.aether.mixin.access.EntityAccessor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.MetaNamedBlockItemProvider;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class Aercloud extends TemplateBlock implements MetaNamedBlockItemProvider {
    public Aercloud(Identifier identifier) {
        super(identifier, Material.ICE);
    }

    public void onEntityCollision(World world, int i, int j, int k, Entity entity) {
        if (!entity.isSneaking()) {
            if (world.getBlockMeta(i, j, k) == 1) {
                entity.velocityY = 2.0;
                /*
                if (!world.isRemote && entity instanceof PlayerEntity player) {
                    AetherAchievements.giveAchievement(AetherAchievements.blueCloud, player);
                }
                 */
            }
            if (entity.velocityY < 0.0) {
                entity.velocityY *= 0.005;
            }
        }
        ((EntityAccessor) entity).setFallDistance(0.0F);
    }

    public boolean isOpaque() {
        return false;
    }

    public int getRenderLayer() {
        return 1;
    }

    protected int getDroppedItemMeta(int i) {
        return i;
    }

    public int getColor(int i) {
        return switch (i) {
            case 1 -> 3714284;
            case 2 -> 16777088;
            default -> 16777215;
        };
    }

    public int getColorMultiplier(BlockView iblockaccess, int i, int j, int k) {
        return this.getColor(iblockaccess.getBlockMeta(i, j, k));
    }

    public boolean isSideVisible(BlockView iblockaccess, int i, int j, int k, int l) {
        return super.isSideVisible(iblockaccess, i, j, k, 1 - l);
    }

    public Box getCollisionShape(World world, int i, int j, int k) {
        return Box.createCached(i, j, k, i + 1, j, k + 1);
    }

    public int[] getValidMetas() {
        return new int[]{0, 1, 2};
    }
}
