package io.github.kaosreven.aether.item.misc;

import io.github.kaosreven.aether.util.MoaColor;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemMoaEgg extends TemplateItem {
    public ItemMoaEgg(Identifier itemID) {
        super(itemID);
        this.setHasSubtypes(true);
    }

    public int getColorMultiplier(int damage) {
        return MoaColor.getColour(damage).colour;
    }

    public String getTranslationKey(ItemStack stack) {
        int i = stack.getDamage();
        if (i > MoaColor.colors.size() - 1) {
            i = MoaColor.colors.size() - 1;
        }

        return this.getTranslationKey() + i;
    }
}
