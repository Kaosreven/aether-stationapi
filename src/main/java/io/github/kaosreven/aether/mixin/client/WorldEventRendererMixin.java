package io.github.kaosreven.aether.mixin.client;

import io.github.kaosreven.aether.entity.AetherParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldEventRendererMixin {
    @Shadow
    private Minecraft client;

    @SuppressWarnings("NameDoesntMatchTargetClass")
    @Inject(method = "addParticle", at = @At(value = "CONSTANT", args = "doubleValue=16.0"), cancellable = true)
    void inject(String particle, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfo ci) {
        AetherParticles.addAetherParticle(client, particle, x, y, z, velocityX, velocityY, velocityZ);
    }
}
