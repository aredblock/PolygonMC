package de.aredblock.polygonmc.events.player;

import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerSpawnEvent;
import net.minestom.server.event.trait.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLoginEvent implements PlayerEvent {

    private final Player player;
    private final PlayerSpawnEvent spawnEvent;

    public PlayerLoginEvent(Player player, PlayerSpawnEvent spawnEvent) {
        this.player = player;
        this.spawnEvent = spawnEvent;
    }

    @Override
    public @NotNull Player getPlayer() {
        return player;
    }

    public PlayerSpawnEvent getSpawnEvent() {
        return spawnEvent;
    }

}
