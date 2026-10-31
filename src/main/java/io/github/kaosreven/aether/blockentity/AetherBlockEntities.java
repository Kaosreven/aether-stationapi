package io.github.kaosreven.aether.blockentity;

import io.github.kaosreven.aether.blockentity.block.BlockEntityEnchanter;
import io.github.kaosreven.aether.blockentity.block.BlockEntityFreezer;
import io.github.kaosreven.aether.blockentity.block.BlockEntityIncubator;
import io.github.kaosreven.aether.blockentity.block.BlockEntityTreasureChest;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;

public class AetherBlockEntities {
    @Entrypoint.Namespace
    public static Namespace MOD_ID = Null.get();

    @EventListener
    public void registerTileEntities(BlockEntityRegisterEvent event) {
        event.register(MOD_ID.id("treasure_chest").toString(), BlockEntityTreasureChest.class);
        event.register(MOD_ID.id("freezer").toString(), BlockEntityFreezer.class);
        event.register(MOD_ID.id("enchanter").toString(), BlockEntityEnchanter.class);
        event.register(MOD_ID.id("incubator").toString(), BlockEntityIncubator.class);
    }
}
