package io.github.kaosreven.aether.block;

import io.github.kaosreven.aether.gen.feature.AetherGenGoldenOak;
import io.github.kaosreven.aether.gen.feature.AetherGenSkyroot;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.template.block.TemplateSaplingBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherSapling extends TemplateSaplingBlock {
    public static int sprSkyroot;
    public static int sprGoldenOak;
    public boolean golden;

    public AetherSapling(Identifier identifier, boolean golden) {
        super(identifier, golden ? sprGoldenOak : sprSkyroot);
        this.golden = golden;
    }

    @Override
    public int getTexture(int i, int j) {
        return golden ? sprGoldenOak : sprSkyroot;
    }

    @Override
    protected boolean canPlantOnTop(int i) {
        return i == AetherBlocks.Grass.id || i == AetherBlocks.Dirt.id;
    }

    @Override
    public void generate(World world, int i, int j, int k, Random random) {
        world.setBlockWithoutNotifyingNeighbors(i, j, k, 0);
        Feature obj;
        if (this.id == AetherBlocks.GoldenOakSapling.id) {
            obj = new AetherGenGoldenOak();
        } else {
            obj = new AetherGenSkyroot();
        }

        if (!obj.generate(world, random, i, j, k)) {
            world.setBlockWithoutNotifyingNeighbors(i, j, k, this.id);
        }

    }
}