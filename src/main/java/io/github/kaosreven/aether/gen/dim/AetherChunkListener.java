package io.github.kaosreven.aether.gen.dim;

import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.gen.feature.dungeons.AetherGenDungeonBronze;
import io.github.kaosreven.aether.gen.feature.dungeons.AetherGenDungeonSilver;
import io.github.kaosreven.aether.gen.feature.dungeons.AetherGenDungeonSilverNew;
import io.github.kaosreven.aether.gen.feature.terrain.AetherGenClouds;
import io.github.kaosreven.aether.gen.feature.terrain.AetherGenGumdrop;
import io.github.kaosreven.aether.gen.feature.terrain.AetherGenMinable;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.gen.feature.PlantPatchFeature;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;

public class AetherChunkListener {
    public static int gumCount;
    private FastRandom random;

    @EventListener
    public void populate(WorldGenEvent.ChunkDecoration event) {
        random = new FastRandom(event.random.nextLong());
        if (event.world.dimension instanceof AetherDimension) populateAether(event);
    }

    public void populateAether (WorldGenEvent.ChunkDecoration event) {
        // Dirt patches
        for (int i = 0; i < 20; ++i) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(128);
            int z = event.z + random.nextInt(16);
            (new AetherGenMinable(AetherBlocks.Dirt.id, 32)).generate(event.world, random, x, y, z);
        }

        // White flowers
        for (int i = 0; i < 2; ++i) {
            int x = event.x + random.nextInt(16) + 8;
            int y = random.nextInt(128);
            int z = event.z + random.nextInt(16) + 8;
            (new PlantPatchFeature(AetherBlocks.WhiteFlower.id)).generate(event.world, random, x, y, z);
        }

        // Purple flowers
        for (int i = 0; i < 2; ++i) {
            if (random.nextInt(2) == 0) {
                int x = event.x + random.nextInt(16) + 8;
                int y = random.nextInt(128);
                int z = event.z + random.nextInt(16) + 8;
                (new PlantPatchFeature(AetherBlocks.PurpleFlower.id)).generate(event.world, random, x, y, z);
            }
        }

        // Icestone ore
        for (int i = 0; i < 10; ++i) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(128);
            int z = event.z + random.nextInt(16);
            (new AetherGenMinable(AetherBlocks.Icestone.id, 32)).generate(event.world, random, x, y, z);
        }

        // Ambrosium ore
        for (int i = 0; i < 20; ++i) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(128);
            int z = event.z + random.nextInt(16);
            (new AetherGenMinable(AetherBlocks.AmbrosiumOre.id, 16)).generate(event.world, random, x, y, z);
        }

        // Zanite ore
        for (int i = 0; i < 15; ++i) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(64);
            int z = event.z + random.nextInt(16);
            (new AetherGenMinable(AetherBlocks.ZaniteOre.id, 8)).generate(event.world, random, x, y, z);
        }

        // Gravitite ore
        for (int i = 0; i < 8; ++i) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(32);
            int z = event.z + random.nextInt(16);
            (new AetherGenMinable(AetherBlocks.GravititeOre.id, 7)).generate(event.world, random, x, y, z);
        }

        // Cold clouds (high altitude)
        if (random.nextInt(50) == 0) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(32) + 96;
            int z = event.z + random.nextInt(16);
            (new AetherGenClouds(AetherBlocks.Aercloud.id, 2, 4, false)).generate(event.world, random, x, y, z);
        }

        // Blue clouds (mid altitude)
        if (random.nextInt(13) == 0) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(64) + 32;
            int z = event.z + random.nextInt(16);
            (new AetherGenClouds(AetherBlocks.Aercloud.id, 1, 8, false)).generate(event.world, random, x, y, z);
        }

        // Regular clouds
        if (random.nextInt(7) == 0) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(64) + 32;
            int z = event.z + random.nextInt(16);
            (new AetherGenClouds(AetherBlocks.Aercloud.id, 0, 16, false)).generate(event.world, random, x, y, z);
        }

        // Golden clouds (low altitude, dense)
        if (random.nextInt(50) == 0) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(32);
            int z = event.z + random.nextInt(16);
            (new AetherGenClouds(AetherBlocks.Aercloud.id, 0, 64, true)).generate(event.world, random, x, y, z);
        }

        // Bronze dungeons
        for (int i = 0; i < 2; ++i) {
            int x = event.x + random.nextInt(16);
            int y = 32 + random.nextInt(64);
            int z = event.z + random.nextInt(16);
            (new AetherGenDungeonBronze(AetherBlocks.LockedDungeonStone.id, AetherBlocks.LockedLightDungeonStone.id, AetherBlocks.DungeonStone.id, AetherBlocks.LightDungeonStone.id, AetherBlocks.Holystone.id, 2, AetherBlocks.Holystone.id, 0, 16, true)).generate(event.world, random, x, y, z);
        }

        // Silver dungeon (rare)
        if (random.nextInt(500) == 0) {
            int x = event.x + random.nextInt(16);
            int y = random.nextInt(32) + 64;
            int z = event.z + random.nextInt(16);
            (new AetherGenDungeonSilverNew(AetherBlocks.LockedDungeonStone.id, AetherBlocks.LockedLightDungeonStone.id, AetherBlocks.DungeonStone.id, AetherBlocks.LightDungeonStone.id, AetherBlocks.Holystone.id, 2, AetherBlocks.Holystone.id, 0, AetherBlocks.Pillar.id)).generate(event.world, random, x, y, z);
        }

        // Gold dungeon (very rare, cooldown-gated)
        if (gumCount < 800) {
            gumCount++;
        } else if (random.nextInt(32) == 0) {
            int x = event.x + random.nextInt(16) + 8;
            int y = random.nextInt(64) + 32;
            int z = event.z + random.nextInt(16) + 8;
            boolean generated = (new AetherGenGumdrop()).generate(event.world, random, x, y, z);
            if (generated) {
                gumCount = 0;
            }
        }
    }
}