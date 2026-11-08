package io.github.kaosreven.aether.blockentity.container;

import io.github.kaosreven.aether.blockentity.block.BlockEntityAetherMachine;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;

public class ContainerIncubator extends ScreenHandler {
    public BlockEntityAetherMachine machine;
    public int burnTime = 0;
    public int itemBurnTime = 0;

    public ContainerIncubator(PlayerInventory inventoryplayer, BlockEntityAetherMachine tileentityIncubator) {
        this.machine = tileentityIncubator;
        this.addSlot(new TileEntityIncubatorSlot(tileentityIncubator, 1, 73, 17));
        this.addSlot(new Slot(tileentityIncubator, 0, 73, 53));

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

    public void sendContentUpdates() {
        super.sendContentUpdates();

        for (int i = 0; i < this.listeners.size(); ++i) {
            ScreenHandlerListener icrafting = (ScreenHandlerListener) this.listeners.get(i);

            if (this.burnTime != this.machine.Progress) {
                icrafting.onPropertyUpdate(this, 0, this.machine.Progress);
            }

            if (this.itemBurnTime != this.machine.PowerRemaining) {
                icrafting.onPropertyUpdate(this, 1, this.machine.PowerRemaining);
            }
        }

        this.burnTime = this.machine.Progress;
        this.itemBurnTime = this.machine.PowerRemaining;
    }

    public void setProperty(int i, int j) {
        if (i == 0) {
            this.machine.Progress = j;
        }

        if (i == 1) {
            this.machine.PowerRemaining = j;
        }
    }

    public boolean canUse(PlayerEntity entityplayer) {
        return this.machine.canPlayerUse(entityplayer);
    }

    static class TileEntityIncubatorSlot extends Slot {
        public TileEntityIncubatorSlot(Inventory inv, int slot, int x, int y) {
            super(inv, slot, x, y);
        }

        public int getMaxItemCount() {
            return 1;
        }
    }
}
