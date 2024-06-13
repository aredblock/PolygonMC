package de.aredblock.polygonmc.commands;

public final class DemoCommandRegistry implements CommandRegistry {

    @RegisterCommand(alias = "demo")
    public void demoCommand(CommandInput input){
        input.getSender().sendMessage("DEMO");
    }

    @RegisterCommand(alias = "helloWorld")
    public void helloWorld(CommandInput input){
        input.getSender().sendMessage("Hello World!");
    }

}
