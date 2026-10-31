package io.github.kaosreven.aether.item.accessory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemAgilityCape extends ItemCape {
    public ItemAgilityCape(Identifier i, int j, String path, int l) {
        super(i, j, path, l);
    }


    @Override
    public void onAccessoryAdded(PlayerEntity player, ItemStack accessory) {
        if (player.stepHeight < 1.0f) player.stepHeight *= 2.0f;
    }

    @Override
    public void onAccessoryRemoved(PlayerEntity player, ItemStack accessory) {
        if (player.stepHeight >= 1.0f) player.stepHeight *= 0.5f;
    }
}
