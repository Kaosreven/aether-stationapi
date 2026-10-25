package io.github.kaosreven.aether.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherGrass extends TemplateBlock {
    public static int sprTop;
    public static int sprSide;

    public AetherGrass(Identifier identifier) {
        super(identifier, Material.SOIL);
        setTickRandomly(true);
        textureId = sprTop;
    }

    @Override
    public int getTexture(int side, int j) {
        return switch (side) {
            case 0 -> AetherBlocks.Dirt.textureId;
            case 1 -> sprTop;
            default -> sprSide;
        };
    }

    public int getTextureId(BlockView iblockaccess, int i, int j, int k, int l) {
        if (l == 1) {
            return sprTop;
        } else {
            return l == 0 ? AetherBlocks.Dirt.textureId : sprSide;
        }
    }

    public void onTick(World world, int i, int j, int k, Random random) {
        if (!world.isRemote) {
            if (world.getLightLevel(i, j + 1, k) < 4 && world.getMaterial(i, j + 1, k).blocksVision()) {
                if (random.nextInt(4) != 0) {
                    return;
                }

                world.setBlock(i, j, k, AetherBlocks.Dirt.id);
            } else if (world.getLightLevel(i, j + 1, k) >= 9) {
                int l = i + random.nextInt(3) - 1;
                int i1 = j + random.nextInt(5) - 3;
                int j1 = k + random.nextInt(3) - 1;
                if (world.getBlockId(l, i1, j1) == AetherBlocks.Dirt.id && world.getLightLevel(l, i1 + 1, j1) >= 4 && !world.getMaterial(l, i1 + 1, j1).blocksVision()) {
                    int meta = world.getBlockMeta(l, i1, j1);
                    world.setBlock(l, i1, j1, AetherBlocks.Grass.id);
                    world.setBlockMeta(l, i1, j1, meta);
                }
            }

        }
    }

    public int getDroppedItemId(int i, Random random) {
        return AetherBlocks.Dirt.getDroppedItemId(0, random);
    }

    public void afterBreak(World world, PlayerEntity entityplayer, int i, int j, int k, int l) {
        //entityplayer.increaseStat(Stats.MINE_BLOCK[this.id], 1);
        /*
        if (UtilSkyroot.shovel(entityplayer) && l == 0) {
            this.dropStacks(world, i, j, k, l);
        }
         */

        this.dropStacks(world, i, j, k, l);
    }

    @Override
    public void onPlaced(World arg, int i, int j, int k) {
        super.onPlaced(arg, i, j, k);
        arg.setBlockMeta(i, j, k, 1);
    }
}
