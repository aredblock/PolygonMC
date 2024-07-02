package de.aredblock.polygonmc.server;

import de.aredblock.polygonmc.commands.CommandInput;
import de.aredblock.polygonmc.commands.CommandRegistry;
import de.aredblock.polygonmc.commands.RegisterCommand;
import de.aredblock.polygonmc.coordinate.Region;
import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import de.aredblock.polygonmc.events.EventCalledEvent;
import de.aredblock.polygonmc.vanilla.entity.FakePlayer;
import de.aredblock.polygonmc.vanilla.schematic.Schematic;
import de.aredblock.polygonmc.vanilla.schematic.SchematicOption;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;
import java.io.File;

public final class MainDemo implements ListenerRegistry, CommandRegistry {

    private static final Region spawnRegion = new Region(new Pos(-10, 30, -10), new Pos(10, 60, 10));

    private static InstanceContainer INSTANCE_CONTAINER;

    public static void main(String[] args) {
        var minecraftServer = MinecraftServer.init();

        INSTANCE_CONTAINER = (InstanceContainer) MinecraftServer.getInstanceManager().generateFlat(Block.STONE);

        MinecraftServer.getCommandManager().registerCommandRegistry(new MainDemo());
        MinecraftServer.getGlobalEventHandler().registerListenerRegistry(new MainDemo());

        MojangAuth.init();

        minecraftServer.start("0.0.0.0", 25565);
    }

    //COMMANDS
    @RegisterCommand(name = "helloWorld")
    public void demoCommand(CommandInput input){
        input.getSender().sendMessage("Hello World!");
    }

    @RegisterCommand(name = "schematicDemo", aliases = { "schematic" })
    public void schematicCommand(CommandInput input){
        if(input.getSender() instanceof Player player){
            try {
                var schematic = new Schematic(new File("demo.schem"))
                        .updateOption(SchematicOption.AIRPLACEMENT, false);
                schematic.paste(player.getInstance(), player.getPosition());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @RegisterCommand(name = "fakePlayer", aliases = { "spawnNpc" })
    public void fakePlayerCommand(CommandInput input){
        if(input.isFromPlayer()){
            var player = input.getPlayer();
            var location = player.getPosition();

            FakePlayer.builder()
                    .skin(player.getSkin())
                    .instance(player.getInstance())
                    .position(location)
                    .build();
        }
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

    @RegisterListener
    public void onEventCalledEvent(EventCalledEvent<?> event){
        //System.out.println(event.getCalledEvent().getClass().getSimpleName());
    }

}