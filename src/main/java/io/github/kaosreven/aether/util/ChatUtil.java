package io.github.kaosreven.aether.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.modificationstation.stationapi.api.util.SideUtil;

import java.util.List;

public class ChatUtil {
    public static int areaOfEffect = 50;
    public static void chatLine(String s, Entity entity) {
        SideUtil.run(() -> chatLineClient(s), () -> chatLineServer(s, entity));
    }

    @Environment(EnvType.CLIENT)
    public static void chatLineClient(String s) {
        Minecraft mc = ((Minecraft) FabricLoader.getInstance().getGameInstance());
        mc.inGameHud.addChatMessage(s);
    }

    @Environment(EnvType.SERVER)
    public static void chatLineServer(String s, Entity entity) {
        List<PlayerEntity> playersNearby = entity.world.collectEntitiesByClass(PlayerEntity.class, Box.create(entity.x - areaOfEffect, entity.y - areaOfEffect, entity.z - areaOfEffect, entity.x + areaOfEffect, entity.y + areaOfEffect, entity.z + areaOfEffect));
        for (PlayerEntity player : playersNearby) {
            player.sendMessage(s);
        }
    }
}