package de.aredblock.polygonmc.addon.loader;

import de.aredblock.polygonmc.addon.api.Addon;
import de.aredblock.polygonmc.addon.api.event.AddonLoadedEvent;
import de.aredblock.polygonmc.addon.messaging.AddonMessageManager;
import net.minestom.server.MinecraftServer;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

//TODO: Add reload method
public final class AddonManager {

    private final File addonsFolder = new File("addons");
    private final AddonMessageManager addonMessageManager = new AddonMessageManager();

    private final List<Addon> addons = new ArrayList<>();

    public AddonManager() {
        if (!addonsFolder.exists()) {
            addonsFolder.mkdirs();
        }
    }

    public void load(){
        var files = Arrays.stream(Objects.requireNonNull(addonsFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jar")))).toList();

        files.forEach(file -> {
            try {
                var classLoader = new URLClassLoader(
                        new URL[] {file.toURI().toURL()},
                        this.getClass().getClassLoader()
                );

                var config = AddonConfigLoader.loadAddonConfig(classLoader);

                var clazz = Class.forName(config.getEntrypoint(), true, classLoader);
                var addon = (Addon) clazz.newInstance();

                initialiseAddon(addon);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    public void initialiseAddon(Addon addon){
        addon.onInitialize();
        addons.add(addon);

        MinecraftServer.getGlobalEventHandler().call(new AddonLoadedEvent(addon));
    }

    public void shutdown(){
        addons.forEach(Addon::onShutdown);
    }

    public List<Addon> getAddons() {
        return addons;
    }

    public AddonMessageManager getAddonMessageManager() {
        return addonMessageManager;
    }

}
