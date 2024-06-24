package de.aredblock.polygonmc.addon;

import de.aredblock.polygonmc.addon.api.Addon;
import de.aredblock.polygonmc.addon.messaging.event.AddonMessageEvent;
import de.aredblock.polygonmc.event.ListenerRegistry;
import de.aredblock.polygonmc.event.RegisterListener;
import net.minestom.server.MinecraftServer;

public final class DemoAddon implements Addon, ListenerRegistry {

    @Override
    public void onInitialize() {
        MinecraftServer.getGlobalEventHandler().registerListenerRegistry(this);
        MinecraftServer.LOGGER.info("DemoAddon initialized!");

        var addonManager = MinecraftServer.getAddonManager();
        addonManager.getAddonMessageManager().sendMessage("demoaddon", "HelloWorld");

        MinecraftServer.LOGGER.info(addonManager.getAddonMessageManager().popMessage("demoaddon"));
    }

    @Override
    public void onShutdown() {
        MinecraftServer.LOGGER.info("DemoAddon shutdown!");
    }

    @RegisterListener
    public void onAddonMessageEvent(AddonMessageEvent event){
        if(event.getChannel().equals("demoaddon")){
            MinecraftServer.LOGGER.info(event.getMessage());
            //event.removeMessage();
        }
    }

}
