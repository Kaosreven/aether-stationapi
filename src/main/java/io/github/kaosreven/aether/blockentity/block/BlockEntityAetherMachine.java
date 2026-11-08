package io.github.kaosreven.aether.blockentity.block;


import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;


public class BlockEntityAetherMachine extends BlockEntity implements Inventory {
    public int Progress = 0;
    public int PowerRemaining = 0;
    public int TimeForItem = 0;
    public ItemStack[] ItemStacks = new ItemStack[3];

    public BlockEntityAetherMachine() {
    }

    public int size() {
        return this.ItemStacks.length;
    }

    public ItemStack getStack(int i) {
        return this.ItemStacks[i];
    }

    public ItemStack removeStack(int i, int j) {
        if (this.ItemStacks[i] != null) {
            ItemStack itemstack1;
            if (this.ItemStacks[i].count <= j) {
                itemstack1 = this.ItemStacks[i];
                this.ItemStacks[i] = null;
            } else {
                itemstack1 = this.ItemStacks[i].split(j);
                if (this.ItemStacks[i].count == 0) {
                    this.ItemStacks[i] = null;
                }

            }
            return itemstack1;
        } else {
            return null;
        }
    }

    public void setStack(int i, ItemStack itemstack) {
        this.ItemStacks[i] = itemstack;
        if (itemstack != null && itemstack.count > this.getMaxCountPerStack()) {
            itemstack.count = this.getMaxCountPerStack();
        }

    }

    public String getName() {
        return "";
    }

    public void readNbt(NbtCompound nbttagcompound) {
        super.readNbt(nbttagcompound);
        NbtList nbttaglist = nbttagcompound.getList("Items");
        this.ItemStacks = new ItemStack[this.size()];

        for (int i = 0; i < nbttaglist.size(); ++i) {
            NbtCompound nbttagcompound1 = (NbtCompound) nbttaglist.get(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < this.ItemStacks.length) {
                this.ItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }

        this.Progress = nbttagcompound.getShort("BurnTime");
        this.TimeForItem = nbttagcompound.getShort("CookTime");
    }

    public void writeNbt(NbtCompound nbttagcompound) {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putShort("BurnTime", (short) this.Progress);
        nbttagcompound.putShort("CookTime", (short) this.TimeForItem);
        NbtList nbttaglist = new NbtList();

        for (int i = 0; i < this.ItemStacks.length; ++i) {
            if (this.ItemStacks[i] != null) {
                NbtCompound nbttagcompound1 = new NbtCompound();
                nbttagcompound1.putByte("Slot", (byte) i);
                this.ItemStacks[i].writeNbt(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.put("Items", nbttaglist);
    }

    public int getMaxCountPerStack() {
        return 64;
    }

    public int getCookProgressScaled(int i) {
        return this.TimeForItem == 0 ? 0 : this.Progress * i / this.TimeForItem;
    }

    public int getBurnTimeRemainingScaled(int i) {
        return this.PowerRemaining * i / 500;
    }

    public boolean isBurning() {
        return this.PowerRemaining > 0;
    }

    @Override
    public void tick() {
    }

    public boolean canPlayerUse(PlayerEntity entityplayer) {
        if (this.world.getBlockEntity(this.x, this.y, this.z) != this) {
            return false;
        } else {
            return entityplayer.getSquaredDistance((double) this.x + 0.5, (double) this.y + 0.5, (double) this.z + 0.5) <= 64.0;
        }
    }
}
