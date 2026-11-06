package io.github.kaosreven.aether.block;

import net.modificationstation.stationapi.api.template.block.TemplatePlantBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class AetherFlower extends TemplatePlantBlock {

    public AetherFlower(Identifier identifier) {
        super(identifier, 0);
        this.setTickRandomly(true);
    }

    @Override
    protected boolean canPlantOnTop(int var1) {
        return var1 == AetherBlocks.Grass.id || var1 == AetherBlocks.Dirt.id;
    }
}
