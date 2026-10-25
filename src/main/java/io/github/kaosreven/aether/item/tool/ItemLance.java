package io.github.kaosreven.aether.item.tool;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.hit.HitResultType;
import net.modificationstation.stationapi.api.item.CustomReachProvider;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemLance extends TemplateSwordItem implements CustomReachProvider {
    private final int weaponDamage;

    public ItemLance(Identifier i, ToolMaterial enumtoolmaterial) {
        super(i, enumtoolmaterial);
        this.setMaxDamage(enumtoolmaterial.getDurability());
        this.weaponDamage = 4 + enumtoolmaterial.getAttackDamage() * 2;
    }

    @Override
    public int getAttackDamage(Entity entity) {
        return this.weaponDamage;
    }

    @Override
    public double getReach(ItemStack itemStack, PlayerEntity playerEntity, HitResultType hitType, double v) {
        return 10.0F;
    }
}
