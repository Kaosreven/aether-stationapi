package io.github.kaosreven.aether.item.misc;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemGummieSwet extends TemplateFoodItem {

    public ItemGummieSwet(Identifier i, int healAmount, boolean isWolfFood) {
        super(i, healAmount, isWolfFood);
        this.maxCount = 64;
        this.setHasSubtypes(true);
    }

    public int getColorMultiplier(int damage) {
        return damage == 1 ? 16777087 : 8765927;
    }

    public String getTranslationKey(ItemStack stack) {
        return this.getTranslationKey() + stack.getDamage();
    }
}
