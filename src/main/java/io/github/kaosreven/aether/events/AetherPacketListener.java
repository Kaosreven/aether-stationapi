package io.github.kaosreven.aether.events;

import io.github.kaosreven.aether.network.MountInputPacket;
import io.github.kaosreven.aether.network.SoundPacket;
import io.github.kaosreven.aether.network.SoundPlayerPacket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.MessageListenerRegistry;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Namespace;

public class AetherPacketListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @EventListener
    public void registerPacket(PacketRegisterEvent event) {
        Registry.register(PacketTypeRegistry.INSTANCE, NAMESPACE.id("sound"), SoundPacket.TYPE);
        Registry.register(PacketTypeRegistry.INSTANCE, NAMESPACE.id("plsound"), SoundPlayerPacket.TYPE);
        Registry.register(MessageListenerRegistry.INSTANCE, NAMESPACE.id("mount_input"), MountInputPacket::handle);
    }

}
