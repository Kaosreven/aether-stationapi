package io.github.kaosreven.aether.mixin;

import io.github.kaosreven.aether.item.AetherItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
abstract public class PlayerMixin extends Entity {
    @Shadow
    public abstract void dropItem(ItemStack arg);

    @Shadow
    public PlayerInventory inventory;

    public PlayerMixin(World arg) {
        super(arg);
    }

    @Inject(method = "initDataTracker", at = @At("RETURN"))
    protected void initDataTrackerPoisonTime(CallbackInfo ci) {
        dataTracker.startTracking(29, (int) 0);
    }
}
