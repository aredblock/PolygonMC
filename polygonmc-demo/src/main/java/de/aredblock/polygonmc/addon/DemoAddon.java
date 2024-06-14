package de.aredblock.polygonmc.addon;

import de.aredblock.polygonmc.addon.api.Addon;
import net.minestom.server.MinecraftServer;

public final class DemoAddon implements Addon {

    @Override
    public void onInitialize() {
        MinecraftServer.LOGGER.info("DemoAddon initialized!");

        var addonManager = MinecraftServer.getAddonManager();
        addonManager.getAddonMessageManager().sendMessage("demoaddon", "HelloWorld");
        MinecraftServer.LOGGER.info(addonManager.getAddonMessageManager().popMessage("demoaddon"));
    }

    @Override
    public void onShutdown() {
        MinecraftServer.LOGGER.info("DemoAddon shutdown!");
    }

}
