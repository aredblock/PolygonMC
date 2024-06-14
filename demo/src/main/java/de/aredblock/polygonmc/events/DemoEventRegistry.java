package de.aredblock.polygonmc.events;

import de.aredblock.polygonmc.event.EventHandler;
import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import de.aredblock.polygonmc.events.player.PlayerLoginEvent;

public final class DemoEventRegistry implements ListenerRegistry {

    @EventHandler
    //or
    @RegisterListener
    public void onPlayerLoginEvent(PlayerLoginEvent event){
        event.getPlayer().sendMessage("Hello World!");
    }

}
