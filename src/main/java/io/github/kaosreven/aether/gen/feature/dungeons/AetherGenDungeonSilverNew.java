package io.github.kaosreven.aether.gen.feature.dungeons;

import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.entity.living.EntityValkyrie;
import io.github.kaosreven.aether.gen.feature.terrain.AetherGenClouds;
import net.minecraft.block.Block;
import net.minecraft.world.World;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import static io.github.kaosreven.aether.block.TreasureChest.PlaceTreasureChest;

public class AetherGenDungeonSilverNew extends AetherGenBuildings {
    private final int baseMeta1;
    private final int baseMeta2;
    private final int lockedBlockID1;
    private final int lockedBlockID2;
    private final int wallBlockID1;
    private final int wallBlockID2;
    private final int baseID1;
    private final int baseID2;
    private final int columnID;
    private final int[][][] rooms = new int[3][3][3];

    public AetherGenDungeonSilverNew(int i, int j, int k, int l, int m, int m1, int o, int o1, int p) {
        lockedBlockID1 = i;
        lockedBlockID2 = j;
        wallBlockID1 = k;
        wallBlockID2 = l;
        baseID1 = m;
        baseMeta1 = m1;
        baseID2 = o;
        baseMeta2 = o1;
        columnID = p;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k) {
        try {
            return generateThrows(world, random, i, j, k);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean generateThrows(World world, Random random, int i, int j, int k) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
        replaceAir = true;
        if (isBoxEmpty(world, i, j, k, 55, 20, 30)) {
            return false;
        } else {
            int row;
            int x;
            int y;
            int z;
            if (world.getBlockId(i, j - 5, k) == 0 || world.getBlockId(i + 55, j - 5, k) == 0 || world.getBlockId(i, j - 5, k + 30) == 0 || world.getBlockId(i + 55, j - 5, k + 30) == 0) {
                for (row = 0; row < 100; ++row) {
                    x = i - 11 + random.nextInt(77);
                    y = j - 7;
                    z = k - 10 + random.nextInt(50);
                    (new AetherGenClouds(AetherBlocks.Aercloud.id, 0, 10, false)).generate(world, random, x, y, z);
                }
            }

            replaceSolid = true;
            setBlocks(baseID2, baseID1, 30);
            setMetadata(baseMeta2, baseMeta1);
            addSolidBox(world, random, i, j - 5, k, 55, 5, 30);

            for (row = i; row < i + 55; row += 4) {
                addColumn(world, random, row, j, k, 14);
                addColumn(world, random, row, j, k + 27, 14);
            }

            for (row = k; row < k + 12; row += 4) {
                addColumn(world, random, i, j, row, 14);
                addColumn(world, random, i + 52, j, row, 14);
            }

            for (row = k + 19; row < k + 30; row += 4) {
                addColumn(world, random, i, j, row, 14);
                addColumn(world, random, i + 52, j, row, 14);
            }

            setBlocks(lockedBlockID1, lockedBlockID2, 20);
            setMetadata(1, 1);
            addHollowBox(world, random, i + 4, j - 1, k + 4, 47, 16, 22);
            addPlaneX(world, random, i + 11, j, k + 5, 15, 20);
            addPlaneX(world, random, i + 18, j, k + 5, 15, 20);
            addPlaneX(world, random, i + 25, j, k + 5, 15, 20);
            addPlaneZ(world, random, i + 5, j, k + 11, 20, 15);
            addPlaneZ(world, random, i + 5, j, k + 18, 20, 15);
            setBlocks(lockedBlockID1, AetherBlocks.Trap.id, 15);
            setMetadata(1, 1);
            addPlaneY(world, random, i + 5, j + 4, k + 5, 20, 20);
            addPlaneY(world, random, i + 5, j + 9, k + 5, 20, 20);

            for (row = j; row < j + 2; ++row) {
                for (x = k + 14; x < k + 16; ++x) {
                    world.setBlockWithoutNotifyingNeighbors(i + 4, row, x, 0);
                }
            }

            setBlocks(0, 0, 1);
            addSolidBox(world, random, i, j - 4, k + 14, 1, 4, 2);
            addSolidBox(world, random, i + 1, j - 3, k + 14, 1, 3, 2);
            addSolidBox(world, random, i + 2, j - 2, k + 14, 1, 2, 2);
            addSolidBox(world, random, i + 3, j - 1, k + 14, 1, 1, 2);
            setBlocks(lockedBlockID1, lockedBlockID2, 20);
            setMetadata(1, 1);

            for (row = 0; row < 7; ++row) {
                addPlaneY(world, random, i - 1, j + 15 + row, k - 1 + 2 * row, 57, 32 - 4 * row);
            }

            row = random.nextInt(3);
            addStaircase(world, random, i + 19, j, k + 5 + row * 7, 10);
            rooms[2][0][row] = 2;
            rooms[2][1][row] = 2;
            rooms[2][2][row] = 1;
            x = i + 25;

            for (y = j; y < j + 2; ++y) {
                for (z = k + 7 + 7 * row; z < k + 9 + 7 * row; ++z) {
                    world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
                }
            }

            row = random.nextInt(3);
            addStaircase(world, random, i + 12, j, k + 5 + row * 7, 5);
            rooms[1][0][row] = 1;
            rooms[1][1][row] = 1;
            row = random.nextInt(3);
            addStaircase(world, random, i + 5, j + 5, k + 5 + row * 7, 5);
            rooms[0][0][row] = 1;
            rooms[0][1][row] = 1;

            int p;
            int r;
            for (p = 0; p < 3; ++p) {
                for (int q = 0; q < 3; ++q) {
                    for (r = 0; r < 3; ++r) {
                        int type = rooms[p][q][r];
                        int roomType;
                        if (p + 1 < 3 && (type == 0 || type == 1 || random.nextBoolean()) && type != 2) {
                            roomType = rooms[p + 1][q][r];
                            if (roomType != 2 && (roomType != 1 || type != 1)) {
                                rooms[p][q][r] = 3;
                                type = 3;
                                x = i + 11 + 7 * p;

                                for (y = j + 5 * q; y < j + 2 + 5 * q; ++y) {
                                    for (z = k + 7 + 7 * r; z < k + 9 + 7 * r; ++z) {
                                        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
                                    }
                                }
                            }
                        }

                        if (p - 1 > 0 && (type == 0 || type == 1 || random.nextBoolean()) && type != 2) {
                            roomType = rooms[p - 1][q][r];
                            if (roomType != 2 && (roomType != 1 || type != 1)) {
                                rooms[p][q][r] = 4;
                                type = 4;
                                x = i + 4 + 7 * p;

                                for (y = j + 5 * q; y < j + 2 + 5 * q; ++y) {
                                    for (z = k + 7 + 7 * r; z < k + 9 + 7 * r; ++z) {
                                        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
                                    }
                                }
                            }
                        }

                        if (r + 1 < 3 && (type == 0 || type == 1 || random.nextBoolean()) && type != 2) {
                            roomType = rooms[p][q][r + 1];
                            if (roomType != 2 && (roomType != 1 || type != 1)) {
                                rooms[p][q][r] = 5;
                                type = 5;
                                z = k + 11 + 7 * r;

                                for (y = j + 5 * q; y < j + 2 + 5 * q; ++y) {
                                    for (x = i + 7 + 7 * p; x < i + 9 + 7 * p; ++x) {
                                        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
                                    }
                                }
                            }
                        }

                        if (r - 1 > 0 && (type == 0 || type == 1 || random.nextBoolean()) && type != 2) {
                            roomType = rooms[p][q][r - 1];
                            if (roomType != 2 && (roomType != 1 || type != 1)) {
                                rooms[p][q][r] = 6;
                                type = 6;
                                z = k + 4 + 7 * r;

                                for (y = j + 5 * q; y < j + 2 + 5 * q; ++y) {
                                    for (x = i + 7 + 7 * p; x < i + 9 + 7 * p; ++x) {
                                        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
                                    }
                                }
                            }
                        }

                        roomType = random.nextInt(3);
                        if (type >= 3) {
                            switch (roomType) {
                                case 0:
                                    world.setBlockWithoutNotifyingNeighbors(i + 7 + p * 7, j - 1 + q * 5, k + 7 + r * 7, AetherBlocks.Trap.id, 1);
                                    break;
                                case 1:
                                    addPlaneY(world, random, i + 7 + 7 * p, j + 5 * q, k + 7 + 7 * r, 2, 2);
                                    int u = i + 7 + 7 * p + random.nextInt(2);
                                    int v = k + 7 + 7 * r + random.nextInt(2);
                                    if (world.getBlockId(u, j + 5 * q + 1, v) != 0) {
                                        break;
                                    }

                                    world.setBlock(u, j + 5 * q + 1, v, AetherBlocks.Chest.id);
                                    world.setBlockMeta(u, j + 5 * q + 1, v, 1);
                                case 2:
                                    addPlaneY(world, random, i + 7 + 7 * p, j + 5 * q, k + 7 + 7 * r, 2, 2);
                                    world.setBlock(i + 7 + 7 * p + random.nextInt(2), j + 5 * q + 1, k + 7 + 7 * r + random.nextInt(2), AetherBlocks.ChestMimic.id);
                                    world.setBlock(i + 7 + 7 * p + random.nextInt(2), j + 5 * q + 1, k + 7 + 7 * r + random.nextInt(2), AetherBlocks.ChestMimic.id);
                            }
                        }
                    }
                }
            }

            for (x = 0; x < 24; ++x) {
                for (z = 0; z < 20; ++z) {
                    p = (int) (Math.sqrt(x * x + (z - 7) * (z - 7)) + Math.sqrt(x * x + (z - 12) * (z - 12)));
                    if (p == 21) {
                        world.setBlockWithoutNotifyingNeighbors(i + 26 + x, j, k + 5 + z, lockedBlockID2, 1);
                    } else if (p > 21) {
                        world.setBlockWithoutNotifyingNeighbors(i + 26 + x, j, k + 5 + z, lockedBlockID1, 1);
                    }
                }
            }

            setBlocks(lockedBlockID1, lockedBlockID1, 1);
            setMetadata(1, 1);
            addPlaneY(world, random, i + 44, j + 1, k + 11, 6, 8);
            addSolidBox(world, random, i + 46, j + 2, k + 13, 4, 2, 4);
            addLineX(world, random, i + 46, j + 4, k + 13, 4);
            addLineX(world, random, i + 46, j + 4, k + 16, 4);
            addPlaneX(world, random, i + 49, j + 4, k + 13, 4, 4);
            setBlocks(Block.WOOL.id, Block.WOOL.id, 1);
            setMetadata(11, 11);
            addPlaneY(world, random, i + 47, j + 3, k + 14, 2, 2);

            for (x = 0; x < 2; ++x) {
                for (z = 0; z < 2; ++z) {
                    world.setBlockWithoutNotifyingNeighbors(i + 44 + x * 5, j + 2, k + 11 + z * 7, AetherBlocks.AmbrosiumTorch.id);
                }
            }

            setBlocks(Block.WATER.id, Block.WATER.id, 1);
            setMetadata(0, 0);
            addPlaneY(world, random, i + 35, j + 1, k + 5, 6, 3);
            addPlaneY(world, random, i + 35, j + 1, k + 22, 6, 3);
            setBlocks(lockedBlockID1, lockedBlockID1, 1);
            setMetadata(1, 1);
            addLineZ(world, random, i + 34, j + 1, k + 5, 2);
            addLineZ(world, random, i + 41, j + 1, k + 5, 2);
            addLineX(world, random, i + 36, j + 1, k + 8, 4);
            world.setBlockWithoutNotifyingNeighbors(i + 35, j + 1, k + 7, lockedBlockID1, 1);
            world.setBlockWithoutNotifyingNeighbors(i + 40, j + 1, k + 7, lockedBlockID1, 1);
            addLineZ(world, random, i + 34, j + 1, k + 23, 2);
            addLineZ(world, random, i + 41, j + 1, k + 23, 2);
            addLineX(world, random, i + 36, j + 1, k + 21, 4);
            world.setBlockWithoutNotifyingNeighbors(i + 35, j + 1, k + 22, lockedBlockID1, 1);
            world.setBlockWithoutNotifyingNeighbors(i + 40, j + 1, k + 22, lockedBlockID1, 1);

            for (x = i + 36; x < i + 40; x += 3) {
                for (z = k + 8; z < k + 22; z += 13) {
                    world.setBlockWithoutNotifyingNeighbors(x, j + 2, z, AetherBlocks.AmbrosiumTorch.id);
                }
            }

            addChandelier(world, i + 28, j, k + 10, 8);
            addChandelier(world, i + 43, j, k + 10, 8);
            addChandelier(world, i + 43, j, k + 19, 8);
            addChandelier(world, i + 28, j, k + 19, 8);
            addSapling(world, random, i + 45, j + 1, k + 6);
            addSapling(world, random, i + 45, j + 1, k + 21);
            EntityValkyrie valk = new EntityValkyrie(world, (double) i + 40.0, (double) j + 1.5, (double) k + 15.0, true);
            valk.setPosition(i + 40, j + 2, k + 15);
            valk.setDungeon(i + 26, j, k + 5);
            world.spawnEntity(valk);
            setBlocks(lockedBlockID1, lockedBlockID1, 1);
            setMetadata(1, 1);
            addHollowBox(world, random, i + 41, j - 2, k + 13, 4, 4, 4);
            x = i + 42 + random.nextInt(2);
            z = k + 14 + random.nextInt(2);
            PlaceTreasureChest(world, x, y - 13, z + 1, 2);
            world.setBlockMetaWithoutNotifyingNeighbors(x, j - 1, z, 2);
            return true;
        }
    }

    private void addSapling(World world, Random random, int i, int j, int k) {
        setBlocks(lockedBlockID1, lockedBlockID1, 1);
        setMetadata(1, 1);
        addPlaneY(world, random, i, j, k, 3, 3);
        world.setBlockWithoutNotifyingNeighbors(i + 1, j, k + 1, AetherBlocks.Dirt.id);
        world.setBlockWithoutNotifyingNeighbors(i + 1, j + 1, k + 1, AetherBlocks.GoldenOakSapling.id);

        for (int x = i; x < i + 3; x += 2) {
            for (int z = k; z < k + 3; z += 2) {
                world.setBlockWithoutNotifyingNeighbors(x, j + 1, z, AetherBlocks.AmbrosiumTorch.id);
            }
        }

    }

    private void addChandelier(World world, int i, int j, int k, int h) {
        int z;
        for (z = j + h + 3; z < j + h + 6; ++z) {
            world.setBlockWithoutNotifyingNeighbors(i, z, k, Block.FENCE.id);
        }

        for (z = i - 1; z < i + 2; ++z) {
            world.setBlockWithoutNotifyingNeighbors(z, j + h + 1, k, Block.GLOWSTONE.id);
        }

        for (z = j + h; z < j + h + 3; ++z) {
            world.setBlockWithoutNotifyingNeighbors(i, z, k, Block.GLOWSTONE.id);
        }

        for (z = k - 1; z < k + 2; ++z) {
            world.setBlockWithoutNotifyingNeighbors(i, j + h + 1, z, Block.GLOWSTONE.id);
        }

    }

    private void addColumn(World world, Random random, int i, int j, int k, int h) {
        setBlocks(wallBlockID1, wallBlockID2, 20);
        setMetadata(1, 1);
        addPlaneY(world, random, i, j, k, 3, 3);
        addPlaneY(world, random, i, j + h, k, 3, 3);
        setBlocks(columnID, columnID, 1);
        setMetadata(0, 0);
        addLineY(world, random, i + 1, j, k + 1, h - 1);
        world.setBlockWithoutNotifyingNeighbors(i + 1, j + h - 1, k + 1, columnID, 1);
    }

    private void addStaircase(World world, Random random, int i, int j, int k, int h) {
        setBlocks(0, 0, 1);
        addSolidBox(world, random, i + 1, j, k + 1, 4, h, 4);
        setBlocks(lockedBlockID1, lockedBlockID2, 5);
        setMetadata(1, 1);addSolidBox(world, random, i + 2, j, k + 2, 2, h + 4, 2);
        world.setBlockWithoutNotifyingNeighbors(i + 1, j, k + 1, Block.SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 2, j, k + 1, Block.DOUBLE_SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 3, j + 1, k + 1, Block.SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 4, j + 1, k + 1, Block.DOUBLE_SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 4, j + 2, k + 2, Block.SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 4, j + 2, k + 3, Block.DOUBLE_SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 4, j + 3, k + 4, Block.SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 3, j + 3, k + 4, Block.DOUBLE_SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 2, j + 4, k + 4, Block.SLAB.id);
        world.setBlockWithoutNotifyingNeighbors(i + 1, j + 4, k + 4, Block.DOUBLE_SLAB.id);
        if (h != 5) {
            world.setBlockWithoutNotifyingNeighbors(i + 1, j + 5, k + 3, Block.SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 1, j + 5, k + 2, Block.DOUBLE_SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 1, j + 6, k + 1, Block.SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 2, j + 6, k + 1, Block.DOUBLE_SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 3, j + 7, k + 1, Block.SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 4, j + 7, k + 1, Block.DOUBLE_SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 4, j + 8, k + 2, Block.SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 4, j + 8, k + 3, Block.DOUBLE_SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 4, j + 9, k + 4, Block.SLAB.id);
            world.setBlockWithoutNotifyingNeighbors(i + 3, j + 9, k + 4, Block.DOUBLE_SLAB.id);
        }
    }
}
