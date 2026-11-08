package io.github.kaosreven.aether.blockentity.container;

import io.github.kaosreven.aether.blockentity.block.BlockEntityAetherMachine;
import io.github.kaosreven.aether.blockentity.block.BlockEntityEnchanter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;

public class ContainerEnchanter extends ScreenHandler {
    public BlockEntityAetherMachine machine;
    public int cookTime = 0;
    public int burnTime = 0;
    public int itemBurnTime = 0;

    public ContainerEnchanter(PlayerInventory inventoryplayer, BlockEntityAetherMachine tileentityenchanter) {
        this.machine = tileentityenchanter;
        this.addSlot(new Slot(tileentityenchanter, 0, 56, 17));
        this.addSlot(new Slot(tileentityenchanter, 1, 56, 53));
        this.addSlot(new FurnaceOutputSlot(inventoryplayer.player, tileentityenchanter, 2, 116, 35));

        int j;
        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(inventoryplayer, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    protected void insertItem(ItemStack itemstack, int i, int j, boolean flag) {
    }

    public void sendContentUpdates() {
        super.sendContentUpdates();

        for (Object listener : this.listeners) {
            ScreenHandlerListener icrafting = (ScreenHandlerListener) listener;
            if (this.cookTime != this.machine.TimeForItem) {
                icrafting.onPropertyUpdate(this, 0, this.machine.TimeForItem);
            }

            if (this.burnTime != this.machine.Progress) {
                icrafting.onPropertyUpdate(this, 1, this.machine.Progress);
            }

            if (this.itemBurnTime != this.machine.PowerRemaining) {
                icrafting.onPropertyUpdate(this, 2, this.machine.PowerRemaining);
            }
        }

        this.cookTime = this.machine.TimeForItem;
        this.burnTime = this.machine.Progress;
        this.itemBurnTime = this.machine.PowerRemaining;
    }

    public void setProperty(int i, int j) {
        if (i == 0) {
            this.machine.TimeForItem = j;
        }

        if (i == 1) {
            this.machine.Progress = j;
        }

        if (i == 2) {
            this.machine.PowerRemaining = j;
        }

    }

    public boolean canUse(PlayerEntity entityplayer) {
        return this.machine.canPlayerUse(entityplayer);
    }

    public ItemStack quickMove(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.slots.get(i);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 2) {
                this.insertItem(itemstack1, 3, 39, true);
            } else if (i >= 3 && i < 30) {
                this.insertItem(itemstack1, 30, 39, false);
            } else if (i >= 30 && i < 39) {
                this.insertItem(itemstack1, 3, 30, false);
            } else {
                this.insertItem(itemstack1, 3, 39, false);
            }

            if (itemstack1.count == 0) {
                slot.setStack(null);
            } else {
                slot.markDirty();
            }

            if (itemstack1.count == itemstack.count) {
                return null;
            }

            slot.onTakeItem(itemstack1);
        }

        return itemstack;
    }
}
