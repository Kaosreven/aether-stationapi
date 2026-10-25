package io.github.kaosreven.aether.block;

import io.github.kaosreven.aether.blockentity.block.BlockEntityTreasureChest;
import io.github.kaosreven.aether.item.AetherItems;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.template.block.TemplateChestBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;
import java.util.Random;

import static io.github.kaosreven.aether.blockentity.AetherBlockEntities.MOD_ID;

public class TreasureChest extends TemplateChestBlock {
    public static int sideTexture;

    public TreasureChest(Identifier identifier) {
        super(identifier);
    }

    public static void PlaceTreasureChest(World world, int x, int y, int z, int rarity) {
        world.setBlockWithoutNotifyingNeighbors(x, y, z, AetherBlocks.TreasureChest.id);
        world.setBlockMetaWithoutNotifyingNeighbors(x, y, z, 1 + rarity);
    }

    @Override
    public int getTextureId(final BlockView tileView, final int x, final int y, final int z, final int meta) {
        if (meta == 0 || meta == 1) {
            return 62;
        }
        final int i1 = tileView.getBlockId(x, y, z - 1);
        final int j1 = tileView.getBlockId(x, y, z + 1);
        final int k1 = tileView.getBlockId(x - 1, y, z);
        final int l1 = tileView.getBlockId(x + 1, y, z);
        byte byte0 = 3;
        if (Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[i1]) {
            byte0 = 2;
        }
        if (Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[l1]) {
            byte0 = 5;
        }
        if (Block.BLOCKS_OPAQUE[l1] && !Block.BLOCKS_OPAQUE[k1]) {
            byte0 = 4;
        }
        return (meta != byte0) ? sideTexture : textureId;
    }

    @Override
    public int getTexture(final int side) {
        if (side == 0 || side == 1) {
            return 62;
        }
        if (side == 3) {
            return textureId;
        }
        return sideTexture;
    }

    @Override
    public void onBreak(World arg, int i, int j, int k) {
        if (arg.getBlockMeta(i, j, k) == 0) super.onBreak(arg, i, j, k);
    }

    public ItemStack getLootFromTag(final Random random, String tag) {
        TagKey<Item> LOOT = TagKey.of(
                ItemRegistry.KEY,
                Identifier.of(MOD_ID, tag)
        );
        List<Item> tagItems = ItemRegistry.INSTANCE.getOrCreateEntryList(LOOT)
                .stream()
                .map(entry -> entry.value())
                .toList();
        Item item = tagItems.get(random.nextInt(tagItems.size()));
        return new ItemStack(item);
    }

    @Override
    public boolean onUse(final World level, final int x, final int y, final int z, final PlayerEntity player) {
        if (level.getBlockEntity(x, y, z) instanceof BlockEntityTreasureChest treasureChest) {
            final int meta = level.getBlockMeta(x, y, z);
            if (meta == 5) {
                GuiHelper.openGUI(player, MOD_ID.id("treasure_chest"), treasureChest, new GenericContainerScreenHandler(player.inventory, treasureChest));
                return true;
            }
            if (meta > 1 && !level.isRemote) {
                treasureChest.setRarity(meta - 1);
                level.setBlockMeta(x, y, z, 1);
            }
            if (meta == 0) {
                GuiHelper.openGUI(player, MOD_ID.id("treasure_chest"), treasureChest, new GenericContainerScreenHandler(player.inventory, treasureChest));
                return true;
            }
            if (level.isRemote) {
                return true;
            }
            final ItemStack itemstack = player.inventory.getSelectedItem();
            if (itemstack != null && meta == 1) {
                if (itemstack.itemId == AetherItems.Key.id && itemstack.getDamage2() + 1 == treasureChest.rarity) {
                    player.inventory.removeStack(player.inventory.selectedSlot, 1);
                    level.setBlockMeta(x, y, z, 0);
                    Random rand = new Random();
                    switch (treasureChest.rarity) {
                        case 0:
                            break;
                        case 1:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                final ItemStack item = this.getLootFromTag(rand, "bronze_dungeon_loot");
                                if (item.getItem() == AetherItems.GummieSwet) {
                                    item.count = rand.nextInt(8);
                                    item.setDamage(rand.nextInt(2));
                                }
                                if (item.getItem() == AetherItems.LightningKnife) {
                                    item.count = rand.nextInt(16);
                                }
                                treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                        case 2:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                final ItemStack item = this.getLootFromTag(rand, "silver_dungeon_loot");
                                if (item.getItem() == AetherItems.GummieSwet) {
                                    item.count = rand.nextInt(16);
                                }
                                treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                        case 3:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                final ItemStack item = this.getLootFromTag(rand, "gold_dungeon_loot");
                                treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                        case 4:
                            for (int p = 0; p < 3 + rand.nextInt(3); ++p) {
                                ItemStack item = this.getLootFromTag(rand, "custom_dungeon_loot");
                                if (item != null)
                                    treasureChest.setStack(rand.nextInt(treasureChest.size()), item);
                            }
                            break;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new BlockEntityTreasureChest();
    }
}
