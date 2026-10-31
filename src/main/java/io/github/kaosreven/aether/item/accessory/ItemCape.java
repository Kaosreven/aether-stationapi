package io.github.kaosreven.aether.item.accessory;

import com.periut.accessoryapi.api.render.AccessoryRenderer;
import com.periut.accessoryapi.api.render.HasCustomRenderer;
import com.periut.accessoryapi.api.render.builtin.CapeRenderer;
import com.periut.accessoryapi.api.render.builtin.ConfigurableRenderer;
import net.modificationstation.stationapi.api.util.Identifier;

import java.awt.*;

public class ItemCape extends ItemMoreArmor implements HasCustomRenderer {

    private ConfigurableRenderer renderer;

    public ItemCape(Identifier i, int j, String path, int l, int m) {
        super(i, j, path, l, m, true);
    }

    public ItemCape(Identifier i, int j, String path, int l, int m, boolean flag) {
        super(i, j, path, l, m, flag);
    }

    public ItemCape(Identifier i, int j, String path, int l) {
        super(i, j, path, l, 16777215, false);
    }

    @Override
    public AccessoryRenderer getRenderer() {
        return renderer;
    }

    @Override
    public void constructRenderer() {
        if (colouriseRender)
            renderer = new CapeRenderer(texture).withColor(new Color(colour));
        else
            renderer = new CapeRenderer(texture);
    }
}
