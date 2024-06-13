package de.aredblock.polygonmc;

import de.aredblock.polygonmc.commands.DemoCommandRegistry;
import de.aredblock.polygonmc.events.DemoEventRegistry;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;

public final class MainDemo {

    public static void main(String[] args) {
        var minecraftServer = MinecraftServer.init();
        var instanceManager = MinecraftServer.getInstanceManager();

        var instanceContainer = instanceManager.createInstanceContainer();

        instanceContainer.setGenerator(unit ->
                unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK));

        var globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final var player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 42, 0));
        });

        MinecraftServer.getCommandManager().registerCommandRegistry(new DemoCommandRegistry());

        MinecraftServer.getGlobalEventHandler().addListenerRegistry(new DemoEventRegistry());

        minecraftServer.start("0.0.0.0", 25565);
    }

}