package io.github.kaosreven.aether.client.entity.renderer.projectile;

import io.github.kaosreven.aether.client.entity.model.ModelHomeShot;
import io.github.kaosreven.aether.entity.projectile.EntityFiroBall;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderFiroBall extends LivingEntityRenderer {
    private final ModelHomeShot shotty;

    public RenderFiroBall(EntityModel ms, float f) {
        super(ms, f);
        this.shotty = (ModelHomeShot) ms;
    }

    public void applyScale(LivingEntity el, float f) {
        EntityFiroBall hs = (EntityFiroBall) el;

        for (int i = 0; i < 3; ++i) {
            this.shotty.sinage[i] = hs.sinage[i];
        }

    }
}
