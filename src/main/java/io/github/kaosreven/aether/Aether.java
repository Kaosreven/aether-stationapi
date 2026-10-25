package io.github.kaosreven.aether;

import io.github.kaosreven.aether.network.MountInputPacket;
import net.fabricmc.api.ModInitializer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Aether implements ModInitializer {
    public static final String MOD_ID = "aether";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        //MountInputPacket.register();
        //String stapi_version = String.valueOf(FabricLoader.getInstance().getModContainer("station-api-base").get().getMetadata().getVersion());
    }
}
