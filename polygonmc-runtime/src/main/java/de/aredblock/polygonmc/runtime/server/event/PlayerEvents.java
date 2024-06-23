package de.aredblock.polygonmc.runtime.server.event;

import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.Instance;

import java.io.File;

public final class PlayerEvents implements ListenerRegistry {

    private Instance world;

    @RegisterListener
    public void onAsyncPlayerConfigurationEvent(AsyncPlayerConfigurationEvent event){
        var worldFolder = new File("world");
        if(world == null){
            if(worldFolder.exists()){
                var world = MinecraftServer.getInstanceManager().generateFromFile(worldFolder);
                event.getPlayer().setRespawnPoint(new Pos(100,100,100));
                event.setSpawningInstance(world);
                this.world = world;
                MinecraftServer.LOGGER.info("World configured");
            }else {
                MinecraftServer.LOGGER.info("No world found!");
            }
        }
        if(world != null){
            event.getPlayer().setRespawnPoint(new Pos(100,100,100));
            event.setSpawningInstance(MinecraftServer.getInstanceManager().generateFromFile(worldFolder));
        }
    }

}
