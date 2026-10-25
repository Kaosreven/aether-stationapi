package io.github.kaosreven.aether.util;

import io.github.kaosreven.aether.network.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.network.packet.PacketHelper;

import java.util.List;

public class AetherMpUtils {

    public static boolean isEnvServ(){
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

    public static boolean isEnvClient(){
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @Environment(EnvType.SERVER)
    public static void soundPacket(World world, int x, int y, int z, String name, float g, float h) {
        List list2 = world.players;
        if (!list2.isEmpty()) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new SoundPacket(name, x, y, z, g, h));
            }
        }
    }

    @Environment(EnvType.SERVER)
    public static void playerSoundPacket(World world, String name, int id, float g, float h) {
        List list2 = world.players;
        if (!list2.isEmpty()) {
            for (int k = 0; k < list2.size(); k++) {
                ServerPlayerEntity player1 = (ServerPlayerEntity) list2.get(k);
                PacketHelper.sendTo(player1, new SoundPlayerPacket(name, id, g, h));
            }
        }
    }
}