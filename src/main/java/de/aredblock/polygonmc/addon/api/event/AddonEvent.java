package de.aredblock.polygonmc.addon.api.event;

import de.aredblock.polygonmc.addon.api.Addon;
import net.minestom.server.event.Event;

public interface AddonEvent extends Event {

    Addon addon();

}
