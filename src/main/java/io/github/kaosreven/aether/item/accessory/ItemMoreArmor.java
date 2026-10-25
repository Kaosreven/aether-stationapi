package io.github.kaosreven.aether.item.accessory;

import com.periut.accessoryapi.api.Accessory;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemMoreArmor extends TemplateItem implements Accessory {
    private static final int[] damageReduceAmountArray = new int[]{3, 8, 6, 3, 0, 1, 0, 0, 0, 0, 2, 0};
    private static final int[] maxDamageArray = new int[]{11, 16, 15, 13, 10, 10, 8, 10, 10, 10, 10, 10};
    public final int armorLevel;
    public final int armorType;
    public final int damageReduceAmount;
    public final int renderIndex;
    protected final int colour;
    public String texture;
    public boolean colouriseRender;

    public ItemMoreArmor(Identifier i, int j, int k, int l, int col) {
        super(i);
        this.armorLevel = j;
        this.armorType = l;
        this.renderIndex = k;
        this.damageReduceAmount = damageReduceAmountArray[l];
        this.setMaxDamage(maxDamageArray[l] * 3 << j);
        this.maxCount = 1;
        this.colour = col;
        this.colouriseRender = true;
    }

    public ItemMoreArmor(Identifier i, int j, int k, int l) {
        this(i, j, k, l, 16777215);
    }

    public ItemMoreArmor(Identifier i, int j, String path, int l) {
        this(i, j, 0, l);
        this.texture = path;
    }

    public ItemMoreArmor(Identifier i, int j, String path, int l, int m) {
        this(i, j, 0, l, m);
        this.texture = path;
    }

    public ItemMoreArmor(Identifier i, int j, String path, int l, int m, boolean flag) {
        this(i, j, path, l, m);
        this.colouriseRender = flag;
    }

    public boolean isTypeValid(int i) {
        if (i == this.armorType) {
            return true;
        } else if (i != 8 && i != 9 || this.armorType != 8 && this.armorType != 9) {
            return (i == 7 || i == 11) && (this.armorType == 7 || this.armorType == 11);
        } else {
            return true;
        }
    }

    public boolean damageType() {
        return this.damageType(this.armorType);
    }

    public boolean damageType(int i) {
        return i < 4 || i == 6 || i == 10;
    }

    public int getColorMultiplier(int i) {
        return this.colour;
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item) {
        return switch (armorType) {
            case 4 -> new String[]{"pendant"};
            case 5 -> new String[]{"cape"};
            case 6 -> new String[]{"shield"};
            case 7 -> new String[]{"misc"};
            case 8 -> new String[]{"ring"};
            case 10 -> new String[]{"gloves"};
            default -> new String[0];
        };
    }
}