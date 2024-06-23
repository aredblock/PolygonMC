package de.aredblock.polygonmc.runtime;


import de.aredblock.polygonmc.runtime.server.PolygonMC;

public final class Bootstrap {

    public static void main(String[] args) {
        var polygon = new PolygonMC();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                polygon.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        try {
            polygon.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}