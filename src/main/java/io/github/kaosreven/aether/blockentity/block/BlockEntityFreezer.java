package io.github.kaosreven.aether.blockentity.block;

import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.item.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

import static io.github.kaosreven.aether.block.AetherBlocks.MOD_ID;

public class BlockEntityFreezer extends BlockEntityAetherMachine implements Inventory {
    public static final List<Frozen> frozen = new ArrayList<>();

    static {
        addFrozen(new ItemStack(Item.WATER_BUCKET, 1), new ItemStack(Block.ICE, 5), 500);
        addFrozen(new ItemStack(AetherItems.Bucket, 1, 8), new ItemStack(Block.ICE, 5), 500);
        addFrozen(new ItemStack(Item.LAVA_BUCKET, 1), new ItemStack(Block.OBSIDIAN, 2), 500);
        addFrozen(new ItemStack(AetherBlocks.Aercloud, 1, 0), new ItemStack(AetherBlocks.Aercloud, 1, 1), 50);
        addFrozen(new ItemStack(AetherItems.GoldPendant, 1), new ItemStack(AetherItems.IcePendant, 1), 2500);
        addFrozen(new ItemStack(AetherItems.GoldRing, 1), new ItemStack(AetherItems.IceRing, 1), 1500);
        addFrozen(new ItemStack(AetherItems.IronRing, 1), new ItemStack(AetherItems.IceRing, 1), 1500);
        addFrozen(new ItemStack(AetherItems.IronPendant, 1), new ItemStack(AetherItems.IcePendant, 1), 2500);
    }

    public Frozen currentFrozen;

    public BlockEntityFreezer() {
    }

    public static void addFrozen(ItemStack from, ItemStack to, int i) {
        frozen.add(new Frozen(from, to, i));
    }

    @Override
    public String getName() {
        return MOD_ID.id("Freezer").path;
    }

    @Override
    public void tick() {
        if (this.PowerRemaining > 0) {
            --this.PowerRemaining;
            if (this.currentFrozen != null) {
                ++this.Progress;
            }
        }

        if (this.currentFrozen != null && (this.ItemStacks[0] == null || this.ItemStacks[0].itemId != this.currentFrozen.frozenFrom.itemId)) {
            this.currentFrozen = null;
            this.Progress = 0;
        }

        if (this.currentFrozen != null && this.Progress >= this.currentFrozen.frozenPowerNeeded) {
            if (this.ItemStacks[2] == null) {
                this.setStack(2, new ItemStack(this.currentFrozen.frozenTo.getItem(), 1, this.currentFrozen.frozenTo.getDamage()));
            } else {
                this.setStack(2, new ItemStack(this.currentFrozen.frozenTo.getItem(), this.getStack(2).count + 1, this.currentFrozen.frozenTo.getDamage()));
            }

            if (this.getStack(0).itemId != Item.WATER_BUCKET.id && this.getStack(0).itemId != Item.LAVA_BUCKET.id) {
                if (this.getStack(0).itemId == AetherItems.Bucket.id) {
                    this.setStack(0, new ItemStack(AetherItems.Bucket));
                } else {
                    this.removeStack(0, 1);
                }
            } else {
                this.setStack(0, new ItemStack(Item.BUCKET));
            }

            this.Progress = 0;
            this.currentFrozen = null;
            this.TimeForItem = 0;
        }

        if (this.PowerRemaining <= 0 && this.currentFrozen != null && this.getStack(1) != null && this.getStack(1).itemId == AetherBlocks.Icestone.asItem().id) {
            this.PowerRemaining += 500;
            this.removeStack(1, 1);
        }

        if (this.currentFrozen == null) {
            ItemStack itemstack = this.getStack(0);

            for (Frozen frozenitem : frozen) {
                if (itemstack != null && frozenitem != null && itemstack.itemId == frozenitem.frozenFrom.itemId && itemstack.getDamage() == frozenitem.frozenFrom.getDamage()) {
                    if (this.ItemStacks[2] == null) {
                        this.currentFrozen = frozenitem;
                        this.TimeForItem = this.currentFrozen.frozenPowerNeeded;
                    } else if (this.ItemStacks[2].itemId == frozenitem.frozenTo.itemId && frozenitem.frozenTo.getItem().getMaxCount() > this.ItemStacks[2].count) {
                        this.currentFrozen = frozenitem;
                        this.TimeForItem = this.currentFrozen.frozenPowerNeeded;
                    }
                }
            }
        }

    }

    public static class Frozen {
        public ItemStack frozenFrom;
        public ItemStack frozenTo;
        public int frozenPowerNeeded;

        public Frozen(ItemStack from, ItemStack to, int i) {
            this.frozenFrom = from;
            this.frozenTo = to;
            this.frozenPowerNeeded = i;
        }
    }

}
