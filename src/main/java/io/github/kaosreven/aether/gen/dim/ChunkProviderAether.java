package io.github.kaosreven.aether.gen.dim;

import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.gen.feature.*;
import io.github.kaosreven.aether.gen.feature.terrain.AetherGenLiquids;
import io.github.kaosreven.aether.gen.feature.terrain.AetherGenQuicksoil;
import net.minecraft.block.Block;
import net.minecraft.block.SandBlock;
import net.minecraft.client.gui.screen.LoadingDisplay;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSource;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.impl.world.chunk.FlattenedChunk;

public class ChunkProviderAether implements ChunkSource {
    private final FastRandom random;
    private final OctavePerlinNoiseSampler minLimitPerlinNoise;
    private final OctavePerlinNoiseSampler maxLimitPerlinNoise;
    private final OctavePerlinNoiseSampler perlinNoise1;
    private final OctavePerlinNoiseSampler perlinNoise2;
    private final World world;
    public OctavePerlinNoiseSampler forestNoise;

    // Noise buffers
    double[] minLimitPerlinNoiseBuffer;
    double[] maxLimitPerlinNoiseBuffer;
    double[] depthNoiseBuffer;

    private double[] heightMap;
    private double[] depthBuffer = new double[256];

    public ChunkProviderAether(World world, long seed) {
        this.world = world;
        this.random = new FastRandom(seed);
        this.minLimitPerlinNoise = new OctavePerlinNoiseSampler(random, 16);
        this.maxLimitPerlinNoise = new OctavePerlinNoiseSampler(random, 16);
        this.perlinNoise1 = new OctavePerlinNoiseSampler(random, 8);
        this.perlinNoise2 = new OctavePerlinNoiseSampler(random, 4);
        this.forestNoise = new OctavePerlinNoiseSampler(random, 8);
    }

    // Build the base terrain using noise generation
    public void buildTerrain(int chunkX, int chunkZ, FlattenedChunk chunk) {
        byte sizeXZ = 2;  // chunk subdivision size
        int sizeXZPlus1 = sizeXZ + 1;
        byte sizeY = 33;  // vertical size
        int sizeYPlus1 = sizeXZ + 1;
        heightMap = generateHeightMap(heightMap, chunkX * sizeXZ, 0, chunkZ * sizeXZ, sizeXZPlus1, sizeY, sizeYPlus1);

        for (int xSection = 0; xSection < sizeXZ; ++xSection) {
            for (int zSection = 0; zSection < sizeXZ; ++zSection) {
                for (int yChunk = 0; yChunk < 32; ++yChunk) {
                    double yLerpAmount = 0.25;
                    double density00 = heightMap[((xSection) * sizeYPlus1 + zSection) * sizeY + yChunk];
                    double density01 = heightMap[((xSection) * sizeYPlus1 + zSection + 1) * sizeY + yChunk];
                    double density10 = heightMap[((xSection + 1) * sizeYPlus1 + zSection) * sizeY + yChunk];
                    double density11 = heightMap[((xSection + 1) * sizeYPlus1 + zSection + 1) * sizeY + yChunk];
                    double yDelta00 = (heightMap[((xSection) * sizeYPlus1 + zSection) * sizeY + yChunk + 1] - density00) * yLerpAmount;
                    double yDelta01 = (heightMap[((xSection) * sizeYPlus1 + zSection + 1) * sizeY + yChunk + 1] - density01) * yLerpAmount;
                    double yDelta10 = (heightMap[((xSection + 1) * sizeYPlus1 + zSection) * sizeY + yChunk + 1] - density10) * yLerpAmount;
                    double yDelta11 = (heightMap[((xSection + 1) * sizeYPlus1 + zSection + 1) * sizeY + yChunk + 1] - density11) * yLerpAmount;

                    for (int ySubChunk = 0; ySubChunk < 4; ++ySubChunk) {
                        double xLerpAmount = 0.125;
                        double density0 = density00;
                        double density1 = density01;
                        double xDelta0 = (density10 - density00) * xLerpAmount;
                        double xDelta1 = (density11 - density01) * xLerpAmount;

                        for (int xSubChunk = 0; xSubChunk < 8; ++xSubChunk) {
                            double zLerpAmount = 0.125;
                            double density = density0;
                            double zDelta = (density1 - density0) * zLerpAmount;

                            for (int zSubChunk = 0; zSubChunk < 8; ++zSubChunk) {
                                // Generate Holystone where density is positive
                                if (density > 0.0) {
                                    int x = xSubChunk + xSection * 8;
                                    int y = yChunk * 4 + ySubChunk;
                                    int z = zSubChunk + zSection * 8;

                                    // Direct section access - work with Block instance directly
                                    var section = chunk.getOrCreateSection(y, false);
                                    if (section != null) {
                                        section.setBlockState(x, y & 15, z, AetherBlocks.Holystone.getDefaultState());
                                    }
                                }

                                density += zDelta;
                            }

                            density0 += xDelta0;
                            density1 += xDelta1;
                        }

                        density00 += yDelta00;
                        density01 += yDelta01;
                        density10 += yDelta10;
                        density11 += yDelta11;
                    }
                }
            }
        }
    }

    // Build the surface layers (grass, dirt, etc.)
    public void buildSurfaces(int chunkX, int chunkZ, FlattenedChunk chunk) {
        double noiseScale = 0.03125;
        depthBuffer = perlinNoise2.create(depthBuffer, chunkX * 16, chunkZ * 16, 0.0, 16, 16, 1, noiseScale * 2.0, noiseScale * 2.0, noiseScale * 2.0);

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 16; ++z) {
                int surfaceDepth = (int) (depthBuffer[x + z * 16] / 3.0 + 3.0 + random.nextDouble() * 0.25);
                int runDepth = -1;

                // Work with Block instances directly
                Block topBlock;
                Block fillerBlock = AetherBlocks.Dirt;
                Block stoneBlock = AetherBlocks.Holystone;

                for (int y = 127; y >= 0; --y) {
                    // Direct section access to get block
                    var section = chunk.getOrCreateSection(y, false);
                    if (section == null) continue;

                    BlockState currentState = section.getBlockState(z, y & 15, x);
                    Block currentBlock = currentState.getBlock();

                    if (currentBlock == Block.BLOCKS[0]) { // Air
                        runDepth = -1;
                    } else if (currentBlock == stoneBlock) {
                        if (runDepth == -1) {
                            if (surfaceDepth <= 0) {
                                topBlock = null; // Air
                                fillerBlock = stoneBlock;
                            } else {
                                topBlock = AetherBlocks.Grass;
                                fillerBlock = AetherBlocks.Dirt;
                            }

                            runDepth = surfaceDepth;

                            // Set top block
                            if (y >= 0 && topBlock != null && topBlock != currentBlock) {
                                section.setBlockState(z, y & 15, x, topBlock.getDefaultState());
                            }
                        } else if (runDepth > 0) {
                            --runDepth;
                            // Set filler block
                            if (fillerBlock != currentBlock) {
                                section.setBlockState(z, y & 15, x, fillerBlock.getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isChunkLoaded(int x, int z) {
        return false;
    }

    public Chunk loadChunk(int chunkX, int chunkZ) {
        return getChunk(chunkX, chunkZ);
    }

    public Chunk getChunk(int chunkX, int chunkZ) {
        random.setSeed((long) chunkX * 341873128712L + (long) chunkZ * 132897987541L);

        // Create FlattenedChunk directly instead of using byte array
        FlattenedChunk chunk = new FlattenedChunk(world, chunkX, chunkZ);

        // Generate terrain directly into FlattenedChunk
        buildTerrain(chunkX, chunkZ, chunk);
        buildSurfaces(chunkX, chunkZ, chunk);
        chunk.populateHeightMap();
        return chunk;
    }

    public void decorate(ChunkSource chunkSource, int chunkX, int chunkZ) {
        try {
            decorateThrows(chunkSource, chunkX, chunkZ);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void decorateThrows(ChunkSource chunkSource, int chunkX, int chunkZ) throws IllegalArgumentException, IllegalAccessException {
        SandBlock.fallInstantly = true;
        int worldX = chunkX << 4;
        int worldZ = chunkZ << 4;
        random.setSeed(world.getSeed());
        long seedModifier1 = random.nextLong() / 2L * 2L + 1L;
        long seedModifier2 = random.nextLong() / 2L * 2L + 1L;
        random.setSeed((long) chunkX * seedModifier1 + (long) chunkZ * seedModifier2 ^ world.getSeed());
        double treeNoiseScale = 0.5;

        // Water lakes
        if (random.nextInt(4) == 0) {
            int x = worldX + random.nextInt(16) + 8;
            int y = random.nextInt(128);
            int z = worldZ + random.nextInt(16) + 8;
            (new LakeFeature(Block.WATER.id)).generate(world, random, x, y, z);
        }

        // Quicksoil patches
        if (random.nextInt(5) == 0) {
            for (int x = worldX; x < worldX + 16; ++x) {
                for (int z = worldZ; z < worldZ + 16; ++z) {
                    for (int y = 0; y < 48; ++y) {
                        if (world.getBlockId(x, y, z) == 0 && world.getBlockId(x, y + 1, z) == AetherBlocks.Grass.id && world.getBlockId(x, y + 2, z) == 0) {
                            (new AetherGenQuicksoil(AetherBlocks.Quicksoil.id)).generate(world, random, x, y, z);
                            y = 128;
                        }
                    }
                }
            }
        }

        // Tree generation
        int treeCount = (int) ((forestNoise.sample((double) worldX * treeNoiseScale, (double) worldZ * treeNoiseScale) / 8.0 + random.nextDouble() * 4.0 + 4.0) / 3.0);
        int extraTrees = 0;
        if (random.nextInt(10) == 0) {
            ++extraTrees;
        }

        extraTrees += treeCount;

        for (int i = 0; i < extraTrees; ++i) {
            int x = worldX + random.nextInt(16) + 8;
            int z = worldZ + random.nextInt(16) + 8;
            Feature treeGenerator = random.nextInt(100) == 0 ? new AetherGenGoldenOak() : new AetherGenSkyroot();
            treeGenerator.prepare(1.0, 1.0, 1.0);
            treeGenerator.generate(world, random, x, world.getTopY(x, z), z);
        }

        // Water springs
        for (int i = 0; i < 50; ++i) {
            int x = worldX + random.nextInt(16) + 8;
            int y = random.nextInt(random.nextInt(120) + 8);
            int z = worldZ + random.nextInt(16) + 8;
            (new AetherGenLiquids(Block.FLOWING_WATER.id)).generate(world, random, x, y, z);
        }

        SandBlock.fallInstantly = false;
    }

    // Generate the heightmap using Perlin noise
    private double[] generateHeightMap(double[] buffer, int xStart, int yStart, int zStart, int xSize, int ySize, int zSize) {
        if (buffer == null) {
            buffer = new double[xSize * ySize * zSize];
        }

        if (depthNoiseBuffer == null) {
            depthNoiseBuffer = new double[xSize * ySize * zSize];
            minLimitPerlinNoiseBuffer = new double[xSize * ySize * zSize];
            maxLimitPerlinNoiseBuffer = new double[xSize * ySize * zSize];
        }

        double xzScale = 684.412;
        double yScale = 684.412;
        depthNoiseBuffer = perlinNoise1.create(depthNoiseBuffer, xStart, yStart, zStart, xSize, ySize, zSize, xzScale / 80.0, yScale / 160.0, xzScale / 80.0);
        minLimitPerlinNoiseBuffer = minLimitPerlinNoise.create(minLimitPerlinNoiseBuffer, xStart, yStart, zStart, xSize, ySize, zSize, xzScale, yScale, xzScale);
        maxLimitPerlinNoiseBuffer = maxLimitPerlinNoise.create(maxLimitPerlinNoiseBuffer, xStart, yStart, zStart, xSize, ySize, zSize, xzScale, yScale, xzScale);
        int index = 0;

        for (int x = 0; x < xSize; ++x) {
            for (int z = 0; z < zSize; ++z) {
                for (int y = 0; y < ySize; ++y) {
                    double density;

                    double minLimit = minLimitPerlinNoiseBuffer[index] / 512.0;
                    double maxLimit = maxLimitPerlinNoiseBuffer[index] / 512.0;
                    double depthNoise = (depthNoiseBuffer[index] / 10.0 + 1.0) / 2.0;
                    if (depthNoise < 0.0) {
                        density = minLimit;
                    } else if (depthNoise > 1.0) {
                        density = maxLimit;
                    } else {
                        density = minLimit + (maxLimit - minLimit) * depthNoise;
                    }

                    density -= 8.0;
                    int topFadeStart = 32;
                    if (y > ySize - topFadeStart) {
                        double topFade = (float) (y - (ySize - topFadeStart)) / ((float) topFadeStart - 1.0F);
                        density = density * (1.0 - topFade) + -30.0 * topFade;
                    }

                    topFadeStart = 8;
                    if (y < topFadeStart) {
                        double bottomFade = (float) (topFadeStart - y) / ((float) topFadeStart - 1.0F);
                        density = density * (1.0 - bottomFade) + -30.0 * bottomFade;
                    }

                    buffer[index] = density;
                    ++index;
                }
            }
        }

        return buffer;
    }

    public boolean save(boolean saveEntities, LoadingDisplay display) {
        return true;
    }

    public String getDebugInfo() {
        return "RandomLevelSource";
    }

    public boolean tick() {
        return false;
    }

    public boolean canSave() {
        return true;
    }
}