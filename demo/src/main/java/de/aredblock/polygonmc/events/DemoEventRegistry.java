package de.aredblock.polygonmc.events;

import de.aredblock.polygonmc.event.EventHandler;
import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import net.minestom.server.event.player.PlayerSpawnEvent;

public final class DemoEventRegistry implements ListenerRegistry {

    @EventHandler
    //or
    @RegisterListener
    public void playerSpawnEvent(PlayerSpawnEvent event){
        if(event.isFirstSpawn()){
            event.getPlayer().sendMessage("Hello World!");
        }
    }

}
