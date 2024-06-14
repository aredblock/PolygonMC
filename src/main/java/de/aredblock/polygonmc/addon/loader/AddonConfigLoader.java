package de.aredblock.polygonmc.addon.loader;

import com.google.gson.Gson;
import de.aredblock.polygonmc.files.FilesHelper;

import java.net.URLClassLoader;

final class AddonConfigLoader {

    private static final Gson gson = new Gson();

    public static Config loadAddonConfig(URLClassLoader loader) throws Exception {
        var json = FilesHelper.inputStreamToString(loader.getResourceAsStream("addon.json"));
        return gson.fromJson(json, Config.class);
    }

    class Config {

        private String name;
        private String addonId;
        private String entrypoint;

        public String getName() {
            return name;
        }

        public String getAddonId() {
            return addonId;
        }

        public String getEntrypoint() {
            return entrypoint;
        }

    }

}
