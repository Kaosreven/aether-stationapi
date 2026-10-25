package io.github.kaosreven.aether.client.entity.renderer.living;

import io.github.kaosreven.aether.client.entity.model.ModelFlyingCow2;
import io.github.kaosreven.aether.entity.living.EntityFlyingCow;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderFlyingCow extends LivingEntityRenderer {

    public RenderFlyingCow(EntityModel modelbase, EntityModel modelbase1, float f) {
        super(modelbase, f);
        this.setDecorationModel(modelbase1);
    }

    protected boolean setWoolColorAndRender(EntityFlyingCow flyingcow, int i, float f) {
        if (i == 0) {
            this.bindTexture("aether:stationapi/textures/mobs/Mob_FlyingPigWings.png");
            ModelFlyingCow2.flyingcow = flyingcow;
            return true;
        } else {
            return false;
        }
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f) {
        return this.setWoolColorAndRender((EntityFlyingCow) entityliving, i, f);
    }
}
