package io.github.kaosreven.aether.client.entity.renderer.projectile;

import io.github.kaosreven.aether.client.entity.model.ModelHomeShot;
import io.github.kaosreven.aether.entity.projectile.EntityHomeShot;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;

public class RenderHomeShot extends LivingEntityRenderer {
    private final ModelHomeShot shotty;

    public RenderHomeShot(EntityModel ms, float f) {
        super(ms, f);
        this.shotty = (ModelHomeShot) ms;
    }

    public void applyScale(LivingEntity el, float f) {
        EntityHomeShot hs = (EntityHomeShot) el;

        for (int i = 0; i < 3; ++i) {
            this.shotty.sinage[i] = hs.sinage[i];
        }

    }
}
