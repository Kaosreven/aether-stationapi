package io.github.kaosreven.aether.gen.biome;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import java.util.Arrays;
import java.util.Random;

public class AetherBiomeSource extends BiomeSource {

    private final Biome biome;
    private final double temperature;


    public AetherBiomeSource(double temperature, World world) {
        biome = AetherBiomes.AETHER;
        this.temperature = temperature;
        //Take it you Buildcraft Combustion Engine and never bother me again
        this.temperatureSampler = new OctaveSimplexNoiseSampler(new Random(world.getSeed() * 9871L), 4);
    }

    @Override
    public Biome getBiome(ChunkPos arg) {
        return super.getBiome(arg);
    }

    @Override
    public Biome getBiome(int x, int z) {
        return biome;
    }

    @Override
    public double getTemperature(int x, int z) {
        return temperature;
    }

    @Override
    public double[] create(double[] temperatures, int x, int z, int xSize, int zSize) {
        int totalSize = xSize * zSize;
        if (temperatures == null || temperatures.length < totalSize)
            temperatures = new double[totalSize];
        Arrays.fill(temperatures, 0, totalSize, temperature);
        return temperatures;
    }

    @Override
    public Biome[] getBiomesInArea(Biome[] biomes, int x, int z, int xSize, int zSize) {
        int totalSize = xSize * zSize;
        if (biomes == null || biomes.length < totalSize)
            biomes = new Biome[totalSize];
        if (temperatureMap == null || temperatureMap.length < totalSize) {
            temperatureMap = new double[totalSize];
            downfallMap = new double[totalSize];
        }
        Arrays.fill(biomes, 0, totalSize, biome);
        return biomes;
    }
}
