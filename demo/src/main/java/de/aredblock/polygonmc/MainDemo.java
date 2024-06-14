package de.aredblock.polygonmc;

import de.aredblock.polygonmc.commands.CommandInput;
import de.aredblock.polygonmc.commands.CommandRegistry;
import de.aredblock.polygonmc.commands.RegisterCommand;
import de.aredblock.polygonmc.coordinate.Region;
import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;

public final class MainDemo implements ListenerRegistry, CommandRegistry {

    private static final Region spawnRegion = new Region(new Pos(-10, 30, -10), new Pos(10, 40, 10));

    private static InstanceContainer INSTANCE_CONTAINER;

    public static void main(String[] args) {
        var minecraftServer = MinecraftServer.init();

        INSTANCE_CONTAINER = (InstanceContainer) MinecraftServer.getInstanceManager().generateFlat(Block.STONE);

        MinecraftServer.getCommandManager().registerCommandRegistry(new MainDemo());
        MinecraftServer.getGlobalEventHandler().addListenerRegistry(new MainDemo());

        minecraftServer.start("0.0.0.0", 25565);
    }

    //COMMANDS
    @RegisterCommand(alias = "helloWorld")
    public void demoCommand(CommandInput input){
        input.getSender().sendMessage("Hello World!");
    }


    //EVENTS
    @RegisterListener
    public void onAsyncPlayerConfigurationEvent(AsyncPlayerConfigurationEvent event){
        final var player = event.getPlayer();
        event.setSpawningInstance(INSTANCE_CONTAINER);
        player.setRespawnPoint(new Pos(0, 42, 0).center());
    }

    @RegisterListener
    public void onPlayerBlockBreakEvent(PlayerBlockBreakEvent event){
        var pos = new Pos(event.getBlockPosition());

        if(spawnRegion.isInRegion(pos)){
            event.setCancelled(true);
        }
    }

}