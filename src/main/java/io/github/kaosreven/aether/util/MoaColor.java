package io.github.kaosreven.aether.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoaColor {
    private static final Random random = new Random();
    public static List<MoaColor> colors = new ArrayList();
    private static int totalChance;

    static {
        new MoaColor(0, 7829503, 3, 100, "Blue");
        new MoaColor(1, 2236962, 8, 5, "Black");
        new MoaColor(2, 16777215, 4, 20, "White");
    }

    public int ID;
    public int colour;
    public int jumps;
    public int chance;
    public String name;

    public MoaColor(int i, int j, int k, int l, String s) {
        this.ID = i;
        this.colour = j;
        this.jumps = k;
        this.chance = l;
        totalChance += l;
        this.name = s;
        colors.add(this);
    }

    public static MoaColor pickRandomMoa() {
        int i = random.nextInt(totalChance);

        for (MoaColor color : colors) {
            if (i < color.chance) {
                return color;
            }

            i -= color.chance;
        }

        return colors.get(0);
    }

    public static MoaColor getColour(int ID) {
        for (MoaColor color : colors) {
            if (color.ID == ID) {
                return color;
            }
        }

        return colors.get(0);
    }

    public String getTexture(boolean saddled) {
        return "aether:stationapi/textures/mobs/" + this.name + (saddled ? "MoaSaddle.png" : "Moa.png");
    }
}
