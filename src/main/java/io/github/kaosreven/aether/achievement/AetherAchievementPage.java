package io.github.kaosreven.aether.achievement;


import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.block.Holystone;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherAchievementPage extends AchievementPage {
    public AetherAchievementPage(Identifier id) {
        super(id);
    }

    @Override
    public int getBackgroundTexture(final Random random, final int i, final int j, int randomizedRow, int currentTexture) {
        int k = Block.SAND.textureId;
        final int l = randomizedRow;
        if (l > 37 || j == 35) {
            k = AetherBlocks.Aercloud.textureId;
        } else if (l == 22) {
            k = ((random.nextInt(2) != 0) ? Holystone.sprMossy : AetherBlocks.GravititeOre.textureId);
        } else if (l == 10) {
            k = AetherBlocks.ZaniteOre.textureId;
        } else if (l == 8) {
            k = AetherBlocks.AmbrosiumOre.textureId;
        } else if (l > 4) {
            k = Holystone.sprNormal;
        } else if (l > 0) {
            k = AetherBlocks.Dirt.textureId;
        }
        return k;
    }
}
