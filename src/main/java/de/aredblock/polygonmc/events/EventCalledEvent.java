package de.aredblock.polygonmc.events;

import net.minestom.server.event.Event;

public final class EventCalledEvent<E extends Event> implements Event {

    private final E calledEvent;

    public EventCalledEvent(E calledEvent) {
        this.calledEvent = calledEvent;
    }

    public Event getCalledEvent() {
        return calledEvent;
    }

}
