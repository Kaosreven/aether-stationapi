package io.github.kaosreven.aether.client.entity.renderer.living;

import io.github.kaosreven.aether.entity.living.EntityWhirly;
import io.github.kaosreven.aether.mixin.access.EntityAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ExplosionParticle;
import net.minecraft.client.particle.FireSmokeParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;

import java.util.Random;

public class RenderWhirly extends EntityRenderer {
    private final Random rand = new Random();

    public RenderWhirly() {
    }

    public void render(Entity entity, double d, double d1, double d2, float f, float f1) {
        EntityWhirly entityWhirl = (EntityWhirly) entity;
        ParticleManager particleManager = ((Minecraft) net.fabricmc.loader.api.FabricLoader.getInstance().getGameInstance()).particleManager;

        double d3 = (float) entityWhirl.x;
        double d4 = (float) entityWhirl.y;
        double d5 = (float) entityWhirl.z;
        int i;
        double d6;
        double d7;
        double d8;
        float f2;
        if (!entityWhirl.evil) {
            for (i = 0; i < 2; ++i) {
                d6 = (float) entityWhirl.x + rand.nextFloat() * 0.25F;
                d7 = (float) entityWhirl.y + entityWhirl.height + 0.125F;
                d8 = (float) entityWhirl.z + rand.nextFloat() * 0.25F;
                f2 = rand.nextFloat() * 360.0F;
                ExplosionParticle entityExplodeFx = new ExplosionParticle(entityWhirl.world, -Math.sin(0.01745329F * f2) * 0.75, d7 - 0.25, Math.cos(0.01745329F * f2) * 0.75, d6, 0.125, d8);
                particleManager.addParticle(entityExplodeFx);
                entityWhirl.fluffies.add(entityExplodeFx);
                entityExplodeFx.renderDistanceMultiplier = 10.0;
                entityExplodeFx.noClip = true;
                ((EntityAccessor)entityExplodeFx).invokeSetBoundingBoxSpacing(0.25F, 0.25F);
                entityExplodeFx.setPosition(entityWhirl.x, entityWhirl.y, entityWhirl.z);
                entityExplodeFx.y = d7;
            }
        } else {
            for (i = 0; i < 3; ++i) {
                d6 = (float) entityWhirl.x + rand.nextFloat() * 0.25F;
                d7 = (float) entityWhirl.y + entityWhirl.height + 0.125F;
                d8 = (float) entityWhirl.z + rand.nextFloat() * 0.25F;
                f2 = rand.nextFloat() * 360.0F;
                FireSmokeParticle entitySmokeFx = new FireSmokeParticle(entityWhirl.world, -Math.sin(0.01745329F * f2) * 0.75, d7 - 0.25, Math.cos(0.01745329F * f2) * 0.75, d6, 0.125, d8, 3.5F);
                particleManager.addParticle(entitySmokeFx);
                entityWhirl.fluffies.add(entitySmokeFx);
                entitySmokeFx.renderDistanceMultiplier = 10.0;
                entitySmokeFx.noClip = true;
                ((EntityAccessor)entitySmokeFx).invokeSetBoundingBoxSpacing(0.25F, 0.25F);
                entitySmokeFx.setPosition(entityWhirl.x, entityWhirl.y, entityWhirl.z);
                entitySmokeFx.y = d7;
            }
        }

        if (!entityWhirl.fluffies.isEmpty()) {
            for (i = 0; i < entityWhirl.fluffies.size(); ++i) {
                Particle entityFx = entityWhirl.fluffies.get(i);
                if (entityFx.dead) {
                    entityWhirl.fluffies.remove(entityFx);
                } else {
                    d6 = (float) entityFx.x;
                    d7 = (float) entityFx.boundingBox.minY;
                    d8 = (float) entityFx.z;
                    double d9 = entityWhirl.getDistance(entityFx);
                    double d10 = d7 - d4;
                    entityFx.velocityY = 0.11500000208616257;
                    double d11 = Math.atan2(d3 - d6, d5 - d8) / 0.01745329424738884;
                    d11 += 160.0;
                    entityFx.velocityX = -Math.cos(0.01745329424738884 * d11) * (d9 * 2.5 - d10) * 0.10000000149011612;
                    entityFx.velocityZ = Math.sin(0.01745329424738884 * d11) * (d9 * 2.5 - d10) * 0.10000000149011612;
                }
            }
        }
    }
}