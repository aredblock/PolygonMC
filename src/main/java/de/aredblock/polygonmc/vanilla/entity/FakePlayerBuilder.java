package de.aredblock.polygonmc.vanilla.entity;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.instance.Instance;
import org.jetbrains.annotations.NotNull;

public final class FakePlayerBuilder {

    private String displayName = "FakePlayer";
    private PlayerSkin skin = PlayerSkin.fromUuid("069a79f4-44e9-4726-a5be-fca90e38aaf5");

    private Instance instance = null;
    private Pos position = null;

    private boolean autoInitialisation = false;

    public FakePlayerBuilder displayName(@NotNull String displayName){
        this.displayName = displayName;
        return this;
    }

    public FakePlayerBuilder skin(@NotNull PlayerSkin skin){
        this.skin = skin;
        return this;
    }

    public FakePlayerBuilder instance(@NotNull Instance instance){
        this.instance = instance;
        autoInitialisation = true;
        return this;
    }

    public FakePlayerBuilder position(@NotNull Pos position){
        this.position = position;
        autoInitialisation = true;
        return this;
    }

    public FakePlayer build(){
        var fakeplayer = new FakePlayer(displayName, skin);

        if(autoInitialisation){
            fakeplayer.spawn();
            fakeplayer.setInstance(instance);
            fakeplayer.teleport(position);
        }
        return fakeplayer;
    }

}
