package de.aredblock.polygonmc.addon.messaging.event;

public final class AddonMessageEvent implements MessageEvent {

    private final String channel;
    private final String message;

    private boolean removeMessage = false;

    public AddonMessageEvent(String channel, String message) {
        this.channel = channel;
        this.message = message;
    }

    public void removeMessage() {
        this.removeMessage = true;
    }

    public boolean isRemoveMessage() {
        return removeMessage;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

}
