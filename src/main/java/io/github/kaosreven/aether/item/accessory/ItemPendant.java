package io.github.kaosreven.aether.item.accessory;

import com.periut.accessoryapi.api.render.AccessoryRenderer;
import com.periut.accessoryapi.api.render.HasCustomRenderer;
import com.periut.accessoryapi.api.render.builtin.NecklaceRenderer;
import net.modificationstation.stationapi.api.util.Identifier;

import java.awt.*;

public class ItemPendant extends ItemMoreArmor implements HasCustomRenderer {
    AccessoryRenderer renderer;

    public ItemPendant(Identifier i, int j, String path, int l, int m) {
        super(i, j, path, l, m);
    }

    public ItemPendant(Identifier i, int j, String path, int l) {
        super(i, j, path, l, 16777215, false);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void constructRenderer() {
        if (colouriseRender)
            renderer = new NecklaceRenderer(texture).withColor(new Color(colour));
        else
            renderer = new NecklaceRenderer(texture);
    }
}
