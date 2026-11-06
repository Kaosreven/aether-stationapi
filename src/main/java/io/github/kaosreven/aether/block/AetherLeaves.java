package io.github.kaosreven.aether.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateTranslucentBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherLeaves extends TemplateTranslucentBlock {
    public static int sprSkyroot;
    public static int sprGoldenOak;
    public boolean golden;
    int[] blocks;

    public AetherLeaves(Identifier identifier, boolean golden) {
        super(identifier, golden ? sprGoldenOak : sprSkyroot, Material.LEAVES, false);
        this.golden = golden;
        this.setTickRandomly(true);
    }

    @Override
    public int getTexture(int i) {
        return golden ? sprGoldenOak : sprSkyroot;
    }

    public int getDroppedItemCount(Random random) {
        if (this.id == AetherBlocks.GoldenOakLeaves.id) {
            return random.nextInt(60) != 0 ? 0 : 1;
        } else {
            return random.nextInt(40) != 0 ? 0 : 1;
        }
    }

    public int getDroppedItemId(int i, Random random) {
        if (this.id == AetherBlocks.SkyrootLeaves.id) {
            return AetherBlocks.SkyrootSapling.asItem().id;
        } else {
            return random.nextInt(10) == 0 ? Item.GOLDEN_APPLE.id : AetherBlocks.GoldenOakSapling.asItem().id;
        }
    }

    public void onBreak(World world, int i, int j, int k) {
        int l = 1;
        int i1 = l + 1;
        if (world.isRegionLoaded(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1)) {
            for (int j1 = -l; j1 <= l; ++j1) {
                for (int k1 = -l; k1 <= l; ++k1) {
                    for (int l1 = -l; l1 <= l; ++l1) {
                        int i2 = world.getBlockId(i + j1, j + k1, k + l1);
                        if (i2 == this.id) {
                            int j2 = world.getBlockMeta(i + j1, j + k1, k + l1);
                            world.setBlockMetaWithoutNotifyingNeighbors(i + j1, j + k1, k + l1, j2 | 8);
                        }
                    }
                }
            }
        }

    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        if (world.isRemote) {
            return;
        }

        int meta = world.getBlockMeta(x, y, z);
        if ((meta & 8) == 0) {
            return;
        }

        final int RADIUS = 4;
        final int BOUND_SIZE = RADIUS + 1;
        final int SIDE = 32;
        final int SIDE_SQUARED = SIDE * SIDE;
        final int HALF_SIDE = SIDE / 2;

        // Index strides for fast neighbor access
        final int STEP_X = SIDE_SQUARED;
        final int STEP_Y = SIDE;
        final int STEP_Z = 1;

        if (this.blocks == null) {
            this.blocks = new int[SIDE * SIDE * SIDE];
        }

        // Ensure the region is fully loaded before processing
        if (!world.isRegionLoaded(x - BOUND_SIZE, y - BOUND_SIZE, z - BOUND_SIZE,
                x + BOUND_SIZE, y + BOUND_SIZE, z + BOUND_SIZE)) {
            return;
        }

        // 1. Populate the local 3D grid with block states
        for (int dx = -RADIUS; dx <= RADIUS; ++dx) {
            for (int dy = -RADIUS; dy <= RADIUS; ++dy) {
                for (int dz = -RADIUS; dz <= RADIUS; ++dz) {
                    int blockId = world.getBlockId(x + dx, y + dy, z + dz);
                    int idx = (dx + HALF_SIDE) * STEP_X + (dy + HALF_SIDE) * STEP_Y + (dz + HALF_SIDE);

                    if (blockId == AetherBlocks.Log.id) {
                        this.blocks[idx] = 0; // Source log distance = 0
                    } else if (blockId == AetherBlocks.SkyrootLeaves.id || blockId == AetherBlocks.GoldenOakLeaves.id) {
                        this.blocks[idx] = -2; // Unprocessed leaves
                    } else {
                        this.blocks[idx] = -1; // Solid block / air / other
                    }
                }
            }
        }

        // 2. Flood-fill distance propagation (Breadth-First Search simulation)
        for (int dist = 1; dist <= 4; ++dist) {
            int targetDist = dist - 1;

            for (int dx = -RADIUS; dx <= RADIUS; ++dx) {
                for (int dy = -RADIUS; dy <= RADIUS; ++dy) {
                    for (int dz = -RADIUS; dz <= RADIUS; ++dz) {
                        int baseIdx = (dx + HALF_SIDE) * STEP_X + (dy + HALF_SIDE) * STEP_Y + (dz + HALF_SIDE);

                        if (this.blocks[baseIdx] == targetDist) {
                            // Check all 6 neighbors using fast stride offsets instead of full recalculation
                            int idxXNeg = baseIdx - STEP_X;
                            if (this.blocks[idxXNeg] == -2) this.blocks[idxXNeg] = dist;

                            int idxXPos = baseIdx + STEP_X;
                            if (this.blocks[idxXPos] == -2) this.blocks[idxXPos] = dist;

                            int idxYNeg = baseIdx - STEP_Y;
                            if (this.blocks[idxYNeg] == -2) this.blocks[idxYNeg] = dist;

                            int idxYPos = baseIdx + STEP_Y;
                            if (this.blocks[idxYPos] == -2) this.blocks[idxYPos] = dist;

                            int idxZNeg = baseIdx - STEP_Z;
                            if (this.blocks[idxZNeg] == -2) this.blocks[idxZNeg] = dist;

                            int idxZPos = baseIdx + STEP_Z;
                            if (this.blocks[idxZPos] == -2) this.blocks[idxZPos] = dist;
                        }
                    }
                }
            }
        }

        // 3. Evaluate center block result
        int centerIdx = HALF_SIDE * STEP_X + HALF_SIDE * STEP_Y + HALF_SIDE;
        if (this.blocks[centerIdx] >= 0) {
            world.setBlockMetaWithoutNotifyingNeighbors(x, y, z, meta & -9);
        } else {
            this.dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
            world.setBlock(x, y, z, 0);
        }
    }

    protected int getDroppedItemMeta(int i) {
        return i & 3;
    }

    public boolean isOpaque() {
        return false;
    }

    public void afterBreak(World world, PlayerEntity entityplayer, int i, int j, int k, int l) {
        if (!world.isRemote && entityplayer.getHand() != null && entityplayer.getHand().itemId == Item.SHEARS.id) {
            this.dropStack(world, i, j, k, new ItemStack(this.asItem().id, 1, l & 3));
        } else {
            super.afterBreak(world, entityplayer, i, j, k, l);
        }

    }

    public boolean isSideVisible(BlockView iblockaccess, int i, int j, int k, int l) {
        iblockaccess.getBlockId(i, j, k);
        return true;
    }
}