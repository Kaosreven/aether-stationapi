package io.github.kaosreven.aether.item.tool;

import io.github.kaosreven.aether.entity.special.EntityMiniCloud;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class ItemCloudStaff extends TemplateItem {
    public ItemCloudStaff(Identifier i) {
        super(i);
        this.maxCount = 1;
        this.setMaxDamage(60);
    }

    private ItemStack useCloudStaff(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        if (!this.cloudsExist(world, entityplayer)) {
            world.spawnEntity(new EntityMiniCloud(world, entityplayer, false));
            world.spawnEntity(new EntityMiniCloud(world, entityplayer, true));
            itemstack.damage(1, null);
        }

        return itemstack;
    }

    public ItemStack use(ItemStack itemstack, World world, PlayerEntity entityplayer) {
        return world.isRemote ? itemstack : useCloudStaff(itemstack, world, entityplayer);
    }

    private boolean cloudsExist(World world, PlayerEntity entityplayer) {
        List list = world.getEntities(entityplayer, entityplayer.boundingBox.expand(128.0, 128.0, 128.0));

        for (Object entity : list) {
            if (entity instanceof EntityMiniCloud) {
                return true;
            }
        }

        return false;
    }
}
