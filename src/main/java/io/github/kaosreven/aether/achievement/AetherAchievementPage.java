package io.github.kaosreven.aether.achievement;


import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.block.Holystone;
import net.modificationstation.stationapi.api.client.gui.screen.achievement.AchievementPage;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherAchievementPage extends AchievementPage {
    public AetherAchievementPage(Identifier id) {
        super(id);
    }

    @Override
    public int getBackgroundTexture(final Random random, final int i, final int j, int randomizedRow, int currentTexture) {
        int k = AetherBlocks.Dirt.textureId;
        if (randomizedRow > 37 || j == 35) {
            k = AetherBlocks.Aercloud.textureId;
        } else if (randomizedRow == 22) {
            k = ((random.nextInt(2) != 0) ? Holystone.sprMossy : AetherBlocks.GravititeOre.textureId);
        } else if (randomizedRow == 10) {
            k = AetherBlocks.ZaniteOre.textureId;
        } else if (randomizedRow == 8) {
            k = AetherBlocks.AmbrosiumOre.textureId;
        } else if (randomizedRow > 4) {
            k = Holystone.sprNormal;
        } else if (randomizedRow > 0) {
            k = AetherBlocks.Dirt.textureId;
        }
        return k;
    }
}
