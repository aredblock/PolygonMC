package de.aredblock.polygonmc;

import de.aredblock.polygonmc.commands.DemoCommandRegistry;
import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;

public final class MainDemo implements ListenerRegistry {

    private static InstanceContainer INSTANCE_CONTAINER;

    public static void main(String[] args) {
        var minecraftServer = MinecraftServer.init();

        INSTANCE_CONTAINER = (InstanceContainer) MinecraftServer.getInstanceManager().generateFlat(Block.STONE);

        MinecraftServer.getCommandManager().registerCommandRegistry(new DemoCommandRegistry());
        MinecraftServer.getGlobalEventHandler().addListenerRegistry(new MainDemo());

        minecraftServer.start("0.0.0.0", 25565);
    }

    @RegisterListener
    public void onAsyncPlayerConfigurationEvent(AsyncPlayerConfigurationEvent event){
        final var player = event.getPlayer();
        event.setSpawningInstance(INSTANCE_CONTAINER);
        player.setRespawnPoint(new Pos(0, 42, 0).center());
    }

}