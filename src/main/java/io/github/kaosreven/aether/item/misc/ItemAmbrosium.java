package io.github.kaosreven.aether.item.misc;

import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemAmbrosium extends TemplateFoodItem {

    public ItemAmbrosium(Identifier identifier, int healAmount, boolean isWolfFood) {
        super(identifier,healAmount,isWolfFood);
        this.maxCount = 64;
    }
}
