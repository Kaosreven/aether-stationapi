package io.github.kaosreven.aether.item.tool;

import io.github.kaosreven.aether.entity.projectile.EntityAetherLightning;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.ArrayList;

public class ItemSwordElemental extends TemplateSwordItem {
    public static ArrayList<Class<? extends LivingEntity>> undead = new ArrayList();
    public static int textureId;

    static {
        undead.add(ZombieEntity.class);
        undead.add(SkeletonEntity.class);
        undead.add(PigZombieEntity.class);
    }

    private final int weaponDamage;
    private final int holyDamage;
    private final Type element;
    private final int colour;

    public ItemSwordElemental(Identifier i, Type element, int colour) {
        super(i, ToolMaterial.DIAMOND);
        setTextureId(textureId);
        this.maxCount = 1;
        this.setMaxDamage(element == Type.Holy ? 128 : 32);
        this.weaponDamage = 4;
        this.holyDamage = 20;
        this.element = element;
        this.colour = colour;
    }

    public int getTextureId(int damage) {
        return textureId;
    }

    public float getMiningSpeedMultiplier(ItemStack itemstack, Block block) {
        return 1.5F;
    }

    public boolean postMine(ItemStack itemstack, int i, int j, int k, int l, LivingEntity entityliving) {
        itemstack.damage(2, entityliving);
        return true;
    }

    public boolean postHit(ItemStack itemstack, LivingEntity entityliving, LivingEntity entityliving1) {
        if (!entityliving.world.isRemote) {
            if (this.element == Type.Fire) {
                entityliving.fireTicks = 600;
            } else if (this.element == Type.Lightning) {
                entityliving.world.spawnEntity(new EntityAetherLightning(entityliving.world, entityliving.x, entityliving.y, entityliving.z));
            }
        }

        itemstack.damage(1, entityliving1);
        return true;
    }

    public int getAttackDamage(Entity entity) {
        if (this.element == Type.Holy && entity instanceof LivingEntity living) {

            for (Class<? extends LivingEntity> cls : undead) {
                if (living.getClass().isAssignableFrom(cls)) {
                    return this.holyDamage;
                }
            }
        }

        return this.weaponDamage;
    }

    public int getColorMultiplier(int i) {
        return this.colour;
    }

    public boolean isHandheld() {
        return true;
    }

    public enum Type {
        Fire,
        Lightning,
        Holy;

        Type() {
        }
    }

}
