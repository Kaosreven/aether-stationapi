package io.github.kaosreven.aether.blockentity.block;

import io.github.kaosreven.aether.block.AetherBlocks;
import io.github.kaosreven.aether.item.AetherItems;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

import static io.github.kaosreven.aether.block.AetherBlocks.MOD_ID;

public class BlockEntityEnchanter extends BlockEntityAetherMachine implements Inventory {
    private static final List<Enchantment> enchantments = new ArrayList<>();

    static {
        addEnchantment(new ItemStack(AetherBlocks.GravititeOre, 1), new ItemStack(AetherBlocks.EnchantedGravitite, 1), 1000);
        addEnchantment(new ItemStack(AetherItems.PickSkyroot, 1), new ItemStack(AetherItems.PickSkyroot, 1), 250);
        addEnchantment(new ItemStack(AetherItems.SwordSkyroot, 1), new ItemStack(AetherItems.SwordSkyroot, 1), 250);
        addEnchantment(new ItemStack(AetherItems.ShovelSkyroot, 1), new ItemStack(AetherItems.ShovelSkyroot, 1), 200);
        addEnchantment(new ItemStack(AetherItems.AxeSkyroot, 1), new ItemStack(AetherItems.AxeSkyroot, 1), 200);
        addEnchantment(new ItemStack(AetherItems.PickHolystone, 1), new ItemStack(AetherItems.PickHolystone, 1), 600);
        addEnchantment(new ItemStack(AetherItems.SwordHolystone, 1), new ItemStack(AetherItems.SwordHolystone, 1), 600);
        addEnchantment(new ItemStack(AetherItems.ShovelHolystone, 1), new ItemStack(AetherItems.ShovelHolystone, 1), 500);
        addEnchantment(new ItemStack(AetherItems.AxeHolystone, 1), new ItemStack(AetherItems.AxeHolystone, 1), 500);
        addEnchantment(new ItemStack(AetherItems.PickZanite, 1), new ItemStack(AetherItems.PickZanite, 1), 2500);
        addEnchantment(new ItemStack(AetherItems.SwordZanite, 1), new ItemStack(AetherItems.SwordZanite, 1), 2500);
        addEnchantment(new ItemStack(AetherItems.ShovelZanite, 1), new ItemStack(AetherItems.ShovelZanite, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.AxeZanite, 1), new ItemStack(AetherItems.AxeZanite, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.PickGravitite, 1), new ItemStack(AetherItems.PickGravitite, 1), 6000);
        addEnchantment(new ItemStack(AetherItems.SwordGravitite, 1), new ItemStack(AetherItems.SwordGravitite, 1), 6000);
        addEnchantment(new ItemStack(AetherItems.ShovelGravitite, 1), new ItemStack(AetherItems.ShovelGravitite, 1), 5000);
        addEnchantment(new ItemStack(AetherItems.AxeGravitite, 1), new ItemStack(AetherItems.AxeGravitite, 1), 5000);
        addEnchantment(new ItemStack(AetherItems.Dart, 1, 0), new ItemStack(AetherItems.Dart, 1, 2), 250);
        addEnchantment(new ItemStack(AetherItems.Bucket, 1, 2), new ItemStack(AetherItems.Bucket, 1, 3), 1000);
        addEnchantment(new ItemStack(Item.RECORD_THIRTEEN, 1), new ItemStack(AetherItems.BlueMusicDisk, 1), 2500);
        addEnchantment(new ItemStack(Item.RECORD_CAT, 1), new ItemStack(AetherItems.BlueMusicDisk, 1), 2500);
        addEnchantment(new ItemStack(Item.LEATHER_HELMET, 1), new ItemStack(Item.LEATHER_HELMET, 1), 400);
        addEnchantment(new ItemStack(Item.LEATHER_CHESTPLATE, 1), new ItemStack(Item.LEATHER_CHESTPLATE, 1), 500);
        addEnchantment(new ItemStack(Item.LEATHER_LEGGINGS, 1), new ItemStack(Item.LEATHER_LEGGINGS, 1), 500);
        addEnchantment(new ItemStack(Item.LEATHER_BOOTS, 1), new ItemStack(Item.LEATHER_BOOTS, 1), 400);
        addEnchantment(new ItemStack(Item.WOODEN_PICKAXE, 1), new ItemStack(Item.WOODEN_PICKAXE, 1), 500);
        addEnchantment(new ItemStack(Item.WOODEN_SHOVEL, 1), new ItemStack(Item.WOODEN_SHOVEL, 1), 400);
        addEnchantment(new ItemStack(Item.WOODEN_SWORD, 1), new ItemStack(Item.WOODEN_SWORD, 1), 500);
        addEnchantment(new ItemStack(Item.WOODEN_AXE, 1), new ItemStack(Item.WOODEN_AXE, 1), 400);
        addEnchantment(new ItemStack(Item.WOODEN_HOE, 1), new ItemStack(Item.WOODEN_HOE, 1), 300);
        addEnchantment(new ItemStack(Item.STONE_PICKAXE, 1), new ItemStack(Item.STONE_PICKAXE, 1), 1000);
        addEnchantment(new ItemStack(Item.STONE_SHOVEL, 1), new ItemStack(Item.STONE_SHOVEL, 1), 750);
        addEnchantment(new ItemStack(Item.STONE_SWORD, 1), new ItemStack(Item.STONE_SWORD, 1), 1000);
        addEnchantment(new ItemStack(Item.STONE_AXE, 1), new ItemStack(Item.STONE_AXE, 1), 750);
        addEnchantment(new ItemStack(Item.STONE_HOE, 1), new ItemStack(Item.STONE_HOE, 1), 750);
        addEnchantment(new ItemStack(Item.IRON_HELMET, 1), new ItemStack(Item.IRON_HELMET, 1), 1500);
        addEnchantment(new ItemStack(Item.IRON_CHESTPLATE, 1), new ItemStack(Item.IRON_CHESTPLATE, 1), 2000);
        addEnchantment(new ItemStack(Item.IRON_LEGGINGS, 1), new ItemStack(Item.IRON_LEGGINGS, 1), 2000);
        addEnchantment(new ItemStack(Item.IRON_BOOTS, 1), new ItemStack(Item.IRON_BOOTS, 1), 1500);
        addEnchantment(new ItemStack(Item.IRON_PICKAXE, 1), new ItemStack(Item.IRON_PICKAXE, 1), 2500);
        addEnchantment(new ItemStack(Item.IRON_SHOVEL, 1), new ItemStack(Item.IRON_SHOVEL, 1), 2000);
        addEnchantment(new ItemStack(Item.IRON_SWORD, 1), new ItemStack(Item.IRON_SWORD, 1), 2500);
        addEnchantment(new ItemStack(Item.IRON_AXE, 1), new ItemStack(Item.IRON_AXE, 1), 1500);
        addEnchantment(new ItemStack(Item.IRON_HOE, 1), new ItemStack(Item.IRON_HOE, 1), 1500);
        addEnchantment(new ItemStack(Item.GOLDEN_HELMET, 1), new ItemStack(Item.GOLDEN_HELMET, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_CHESTPLATE, 1), new ItemStack(Item.GOLDEN_CHESTPLATE, 1), 1200);
        addEnchantment(new ItemStack(Item.GOLDEN_LEGGINGS, 1), new ItemStack(Item.GOLDEN_LEGGINGS, 1), 1200);
        addEnchantment(new ItemStack(Item.GOLDEN_BOOTS, 1), new ItemStack(Item.GOLDEN_BOOTS, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_PICKAXE, 1), new ItemStack(Item.GOLDEN_PICKAXE, 1), 1500);
        addEnchantment(new ItemStack(Item.GOLDEN_SHOVEL, 1), new ItemStack(Item.GOLDEN_SHOVEL, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_SWORD, 1), new ItemStack(Item.GOLDEN_SWORD, 1), 1500);
        addEnchantment(new ItemStack(Item.GOLDEN_AXE, 1), new ItemStack(Item.GOLDEN_AXE, 1), 1000);
        addEnchantment(new ItemStack(Item.GOLDEN_HOE, 1), new ItemStack(Item.GOLDEN_HOE, 1), 1000);
        addEnchantment(new ItemStack(Item.DIAMOND_HELMET, 1), new ItemStack(Item.DIAMOND_HELMET, 1), 5000);
        addEnchantment(new ItemStack(Item.DIAMOND_CHESTPLATE, 1), new ItemStack(Item.DIAMOND_CHESTPLATE, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_LEGGINGS, 1), new ItemStack(Item.DIAMOND_LEGGINGS, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_BOOTS, 1), new ItemStack(Item.DIAMOND_BOOTS, 1), 5000);
        addEnchantment(new ItemStack(Item.DIAMOND_PICKAXE, 1), new ItemStack(Item.DIAMOND_PICKAXE, 1), 7000);
        addEnchantment(new ItemStack(Item.DIAMOND_SHOVEL, 1), new ItemStack(Item.DIAMOND_SHOVEL, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_SWORD, 1), new ItemStack(Item.DIAMOND_SWORD, 1), 6500);
        addEnchantment(new ItemStack(Item.DIAMOND_AXE, 1), new ItemStack(Item.DIAMOND_AXE, 1), 6000);
        addEnchantment(new ItemStack(Item.DIAMOND_HOE, 1), new ItemStack(Item.DIAMOND_HOE, 1), 6000);
        addEnchantment(new ItemStack(Item.FISHING_ROD, 1), new ItemStack(Item.FISHING_ROD, 1), 500);
        addEnchantment(new ItemStack(AetherBlocks.Quicksoil, 1), new ItemStack(AetherBlocks.QuicksoilGlass, 1), 250);
        addEnchantment(new ItemStack(AetherBlocks.Holystone, 1), new ItemStack(AetherItems.HealingStone, 1), 750);
        addEnchantment(new ItemStack(AetherItems.GravititeHelmet, 1), new ItemStack(AetherItems.GravititeHelmet, 1), 12000);
        addEnchantment(new ItemStack(AetherItems.GravititeBodyplate, 1), new ItemStack(AetherItems.GravititeBodyplate, 1), 20000);
        addEnchantment(new ItemStack(AetherItems.GravititePlatelegs, 1), new ItemStack(AetherItems.GravititePlatelegs, 1), 15000);
        addEnchantment(new ItemStack(AetherItems.GravititeBoots, 1), new ItemStack(AetherItems.GravititeBoots, 1), 12000);
        addEnchantment(new ItemStack(AetherItems.GravititeGlove, 1), new ItemStack(AetherItems.GravititeGlove, 1), 10000);
        addEnchantment(new ItemStack(AetherItems.ZaniteHelmet, 1), new ItemStack(AetherItems.ZaniteHelmet, 1), 6000);
        addEnchantment(new ItemStack(AetherItems.ZaniteChestplate, 1), new ItemStack(AetherItems.ZaniteChestplate, 1), 10000);
        addEnchantment(new ItemStack(AetherItems.ZaniteLeggings, 1), new ItemStack(AetherItems.ZaniteLeggings, 1), 8000);
        addEnchantment(new ItemStack(AetherItems.ZaniteBoots, 1), new ItemStack(AetherItems.ZaniteBoots, 1), 5000);
        addEnchantment(new ItemStack(AetherItems.ZaniteGlove, 1), new ItemStack(AetherItems.ZaniteGlove, 1), 4000);
        addEnchantment(new ItemStack(AetherItems.ZaniteRing, 1), new ItemStack(AetherItems.ZaniteRing, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.ZanitePendant, 1), new ItemStack(AetherItems.ZanitePendant, 1), 2000);
        addEnchantment(new ItemStack(AetherItems.LeatherGlove, 1), new ItemStack(AetherItems.LeatherGlove, 1), 300);
        addEnchantment(new ItemStack(AetherItems.IronGlove, 1), new ItemStack(AetherItems.IronGlove, 1), 1200);
        addEnchantment(new ItemStack(AetherItems.GoldGlove, 1), new ItemStack(AetherItems.GoldGlove, 1), 800);
        addEnchantment(new ItemStack(AetherItems.DiamondGlove, 1), new ItemStack(AetherItems.DiamondGlove, 1), 4000);
        addEnchantment(new ItemStack(AetherItems.DartShooter, 1, 0), new ItemStack(AetherItems.DartShooter, 1, 2), 2000);
    }

    private Enchantment currentEnchantment;

    public BlockEntityEnchanter() {
    }

    public static void addEnchantment(ItemStack from, ItemStack to, int i) {
        enchantments.add(new Enchantment(from, to, i));
    }

    @Override
    public String getName() {
        return MOD_ID.id("Enchanter").path;
    }

    public void tick() {
        if (this.PowerRemaining > 0) {
            --this.PowerRemaining;
            if (this.currentEnchantment != null) {
                ++this.Progress;
            }
        }

        if (this.currentEnchantment != null && (this.ItemStacks[0] == null || this.ItemStacks[0].itemId != this.currentEnchantment.enchantFrom.itemId)) {
            this.currentEnchantment = null;
            this.Progress = 0;
        }

        if (this.currentEnchantment != null && this.Progress >= this.currentEnchantment.enchantPowerNeeded) {
            if (this.ItemStacks[2] == null) {
                this.setStack(2, new ItemStack(this.currentEnchantment.enchantTo.getItem(), 1, this.currentEnchantment.enchantTo.getDamage()));
            } else {
                this.setStack(2, new ItemStack(this.currentEnchantment.enchantTo.getItem(), this.getStack(2).count + 1, this.currentEnchantment.enchantTo.getDamage()));
            }

            this.removeStack(0, 1);
            this.Progress = 0;
            this.currentEnchantment = null;
            this.TimeForItem = 0;
        }

        if (this.PowerRemaining <= 0 && this.currentEnchantment != null && this.getStack(1) != null && this.getStack(1).itemId == AetherItems.AmbrosiumShard.id) {
            this.PowerRemaining += 500;
            this.removeStack(1, 1);
        }

        if (this.currentEnchantment == null) {
            ItemStack itemstack = this.getStack(0);

            for (Enchantment enchantment : enchantments) {
                if (itemstack != null && enchantment != null && itemstack.itemId == enchantment.enchantFrom.itemId) {
                    if (this.ItemStacks[2] == null) {
                        this.currentEnchantment = enchantment;
                        this.TimeForItem = this.currentEnchantment.enchantPowerNeeded;
                    } else if (this.ItemStacks[2].itemId == enchantment.enchantTo.itemId && enchantment.enchantTo.getItem().getMaxCount() > this.ItemStacks[2].count) {
                        this.currentEnchantment = enchantment;
                        this.TimeForItem = this.currentEnchantment.enchantPowerNeeded;
                    }
                }
            }
        }

    }

    static class Enchantment {
        public ItemStack enchantFrom;
        public ItemStack enchantTo;
        public int enchantPowerNeeded;

        public Enchantment(ItemStack from, ItemStack to, int i) {
            this.enchantFrom = from;
            this.enchantTo = to;
            this.enchantPowerNeeded = i;
        }
    }
}
