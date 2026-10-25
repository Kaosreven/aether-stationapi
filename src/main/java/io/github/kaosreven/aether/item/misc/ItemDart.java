package io.github.kaosreven.aether.item.misc;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemDart extends TemplateItem {
    public static int sprGolden;
    public static int sprEnchanted;
    public static int sprPoison;

    public ItemDart(Identifier itemID) {
        super(itemID);
        this.setHasSubtypes(true);
    }

    public int getTextureId(int damage) {
        return switch (damage) {
            case 1 -> sprPoison;
            case 2 -> sprEnchanted;
            default -> sprGolden;
        };
    }

    public String getTranslationKey(ItemStack stack) {
        int i = stack.getDamage();
        return this.getTranslationKey() + i;
    }
}
