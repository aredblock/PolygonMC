package de.aredblock.polygonmc.addon.messaging;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public final class AddonMessageManager {

    private final Map<String, Stack<String>> channels = new HashMap<>();

    public void sendMessage(String channel, String message) {
        if(channels.containsKey(channel)){
            channels.get(channel).push(message);
            return;
        }

        var stack = new Stack<String>();
        stack.push(message);
        channels.put(channel, stack);
    }

    public String popMessage(String channel) {
        return channels.get(channel).pop();
    }

    public String getNextMessage(String channel) {
        return channels.get(channel).peek();
    }

}
