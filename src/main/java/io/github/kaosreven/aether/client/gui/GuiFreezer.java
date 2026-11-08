package io.github.kaosreven.aether.client.gui;

import io.github.kaosreven.aether.blockentity.block.BlockEntityAetherMachine;
import net.minecraft.entity.player.PlayerInventory;


public class GuiFreezer extends GuiEnchanter {
    public GuiFreezer(PlayerInventory inventoryplayer, BlockEntityAetherMachine tileentityFreezer) {
        super(inventoryplayer, tileentityFreezer);
        this.Inventory = tileentityFreezer;
    }
}
