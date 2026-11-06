package io.github.kaosreven.aether.blockentity.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ContainerLore extends ScreenHandler {
    public Inventory loreSlot = new Inventory() {
        private final ItemStack[] stacks = new ItemStack[1];

        @Override
        public int size() {
            return 1;
        }

        @Override
        public ItemStack getStack(int slot) {
            return this.stacks[slot];
        }

        @Override
        public ItemStack removeStack(int slot, int amount) {
            if (this.stacks[slot] != null) {
                ItemStack stack;
                if (this.stacks[slot].count <= amount) {
                    stack = this.stacks[slot];
                    this.stacks[slot] = null;
                    return stack;
                } else {
                    stack = this.stacks[slot].split(amount);
                    if (this.stacks[slot].count == 0) {
                        this.stacks[slot] = null;
                    }
                    return stack;
                }
            }
            return null;
        }

        @Override
        public void setStack(int slot, ItemStack stack) {
            this.stacks[slot] = stack;
            if (stack != null && stack.count > this.getMaxCountPerStack()) {
                stack.count = this.getMaxCountPerStack();
            }
        }

        @Override
        public String getName() {
            return "Lore Item";
        }

        @Override
        public int getMaxCountPerStack() {
            return 1;
        }

        @Override
        public void markDirty() {

        }

        @Override
        public boolean canPlayerUse(PlayerEntity player) {
            return true;
        }
    };

    public ContainerLore(PlayerInventory inventoryplayer) {
        this.addSlot(new Slot(this.loreSlot, 0, 82, 66));

        int j1;
        for (j1 = 0; j1 < 3; ++j1) {
            for (int l1 = 0; l1 < 9; ++l1) {
                this.addSlot(new Slot(inventoryplayer, l1 + j1 * 9 + 9, 48 + l1 * 18, 113 + j1 * 18));
            }
        }

        for (j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(inventoryplayer, j1, 48 + j1 * 18, 171));
        }

    }

    protected void insertItem(ItemStack itemstack, int i, int j, boolean flag) {
    }

    public void onClosed(PlayerEntity entityplayer) {
        super.onClosed(entityplayer);
        ItemStack itemstack = this.loreSlot.getStack(0);
        if (itemstack != null) {
            entityplayer.dropItem(itemstack);
        }

    }

    public boolean canUse(PlayerEntity entityplayer) {
        return true;
    }

    public ItemStack quickMove(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.slots.get(i);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 0) {
                this.insertItem(itemstack1, 10, 46, true);
            } else if (i >= 10 && i < 37) {
                this.insertItem(itemstack1, 37, 46, false);
            } else if (i >= 37 && i < 46) {
                this.insertItem(itemstack1, 10, 37, false);
            } else {
                this.insertItem(itemstack1, 10, 46, false);
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
