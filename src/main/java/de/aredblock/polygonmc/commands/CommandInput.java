package de.aredblock.polygonmc.commands;

import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.entity.Player;

public final class CommandInput {

    private final CommandSender sender;
    private final CommandContext context;

    public CommandInput(CommandSender sender, CommandContext context) {
        this.sender = sender;
        this.context = context;
    }

    public Player getPlayer() {
        return isFromPlayer() ? (Player) sender : null;
    }

    public boolean isFromPlayer(){
        return sender instanceof Player;
    }

    public CommandSender getSender() {
        return sender;
    }

    public CommandContext getContext() {
        return context;
    }

}
