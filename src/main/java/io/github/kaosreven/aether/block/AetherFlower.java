package io.github.kaosreven.aether.block;

import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplatePlantBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class AetherFlower extends TemplatePlantBlock {

    public AetherFlower(Identifier identifier) {
        super(identifier, 0);
        this.setTickRandomly(true);
        //float var3 = 0.2F;
        //this.setBoundingBox(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 3.0F, 0.5F + var3);
    }

    @Override
    public boolean canPlaceAt(World var1, int var2, int var3, int var4) {
        return super.canPlaceAt(var1, var2, var3, var4) && this.canPlantOnTop(var1.getBlockId(var2, var3 - 1, var4));
    }

    @Override
    protected boolean canPlantOnTop(int var1) {
        return var1 == AetherBlocks.Grass.id || var1 == AetherBlocks.Dirt.id;
    }

    @Override
    public void neighborUpdate(World var1, int var2, int var3, int var4, int var5) {
        super.neighborUpdate(var1, var2, var3, var4, var5);
        this.func_268_h(var1, var2, var3, var4);
    }

    @Override
    public void onTick(World var1, int var2, int var3, int var4, Random var5) {
        this.func_268_h(var1, var2, var3, var4);
    }

    protected final void func_268_h(World var1, int var2, int var3, int var4) {
        if (!this.canGrow(var1, var2, var3, var4)) {
            this.dropStacks(var1, var2, var3, var4, var1.getBlockMeta(var2, var3, var4));
            var1.setBlock(var2, var3, var4, 0);
        }

    }

    @Override
    public boolean canGrow(World var1, int var2, int var3, int var4) {
        return (var1.getBrightness(var2, var3, var4) >= 8 || var1.hasSkyLight(var2, var3, var4)) && this.canPlantOnTop(var1.getBlockId(var2, var3 - 1, var4));
    }
}
