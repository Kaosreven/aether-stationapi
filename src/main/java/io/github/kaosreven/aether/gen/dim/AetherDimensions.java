package io.github.kaosreven.aether.gen.dim;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.dimension.Dimension;
import net.modificationstation.stationapi.api.event.registry.DimensionRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.DimensionContainer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class AetherDimensions {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();
    public static Identifier THE_AETHER;

    @EventListener
    public void registerDimensions(DimensionRegistryEvent event) {
        event.registry.register(THE_AETHER = Identifier.of(MOD_ID, "the_aether"), new DimensionContainer<Dimension>(AetherDimension::new));
    }
}
