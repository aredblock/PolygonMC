package de.aredblock.polygonmc.vanilla.entity;

import net.minestom.server.entity.*;
import net.minestom.server.network.packet.server.play.EntityMetaDataPacket;
import net.minestom.server.network.packet.server.play.PlayerInfoRemovePacket;
import net.minestom.server.network.packet.server.play.PlayerInfoUpdatePacket;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

/*
* Inspired by https://gist.github.com/mworzala/2c5da51204c45c70db771d0ce7fe9412
 */
public class FakePlayer extends Entity {

    private final String displayName;

    private final String skinTexture;
    private final String skinSignature;

    FakePlayer(String displayName, PlayerSkin skin){
        super(EntityType.PLAYER);
        this.displayName = displayName;

        this.skinTexture = skin.textures();
        this.skinSignature = skin.signature();
    }

    public static FakePlayerBuilder builder(){
        return new FakePlayerBuilder();
    }

    @Override
    public void updateNewViewer(@NotNull Player player) {
        var properties = new ArrayList<PlayerInfoUpdatePacket.Property>();
        if (skinTexture != null && skinSignature != null) {
            properties.add(new PlayerInfoUpdatePacket.Property("textures", skinTexture, skinSignature));
        }
        var entry = new PlayerInfoUpdatePacket.Entry(getUuid(), displayName, properties, false,
                0, GameMode.SURVIVAL, null, null);
        player.sendPacket(new PlayerInfoUpdatePacket(PlayerInfoUpdatePacket.Action.ADD_PLAYER, entry));

        super.updateNewViewer(player);

        player.sendPackets(new EntityMetaDataPacket(getEntityId(), Map.of(17, Metadata.Byte((byte) 127))));
    }

    @Override
    public void updateOldViewer(@NotNull Player player) {
        super.updateOldViewer(player);

        player.sendPacket(new PlayerInfoRemovePacket(getUuid()));
    }

}
