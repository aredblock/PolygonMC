package de.aredblock.polygonmc.commands;

import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.CommandContext;

public final class CommandInput {

    private final CommandSender sender;
    private final CommandContext context;

    public CommandInput(CommandSender sender, CommandContext context) {
        this.sender = sender;
        this.context = context;
    }

    public CommandSender getSender() {
        return sender;
    }

    public CommandContext getContext() {
        return context;
    }

}
