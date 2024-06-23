package de.aredblock.polygonmc.runtime.server;

import de.aredblock.polygonmc.runtime.config.ServerConfig;
import de.aredblock.polygonmc.runtime.server.event.PlayerEvents;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;

public final class PolygonMC {

    private ServerConfig config;

    public void start() throws Exception {
        var minecraft = MinecraftServer.init();

        config = ServerConfig.load();
        MinecraftServer.LOGGER.info("Loaded config");

        if(config.getDefaultWorldLoading()){
            MinecraftServer.getGlobalEventHandler().registerListenerRegistry(new PlayerEvents());
        }

        if(config.getOnlineMode()){
            MojangAuth.init();
        }

        MinecraftServer.LOGGER.info("Server is starting on " + config.getPort());
        minecraft.start("localhost", config.getPort());
    }

    public void stop() throws Exception {
        MinecraftServer.stopCleanly();
    }

}
