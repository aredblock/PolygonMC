package de.aredblock.polygonmc.addon.api.event;

import de.aredblock.polygonmc.addon.api.Addon;

public record AddonLoadedEvent(Addon addon) implements AddonEvent {

    @Override
    public Addon addon() {
        return null;
    }

}
