package de.aredblock.polygonmc.runtime.config;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.nio.file.Files;

public final class ServerConfig {

    private Integer port = 25565;

    private Boolean defaultWorldLoading = true;
    private Boolean onlineMode = false;

    public static ServerConfig load() throws Exception {
        var file = new File("config.json");
        var gson = new GsonBuilder().setPrettyPrinting().create();
        var config = new ServerConfig();

        if(!file.exists()){
            var json = gson.toJson(config);
            Files.write(file.toPath(), json.getBytes());
        }

        config = gson.fromJson(new String(Files.readAllBytes(file.toPath())), ServerConfig.class);

        return config;
    }

    public Integer getPort() {
        return port;
    }

    public Boolean getDefaultWorldLoading() {
        return defaultWorldLoading;
    }

    public Boolean getOnlineMode() {
        return onlineMode;
    }

}
