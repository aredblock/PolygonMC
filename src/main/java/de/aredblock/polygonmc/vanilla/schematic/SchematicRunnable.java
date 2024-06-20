package de.aredblock.polygonmc.vanilla.schematic;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;

public interface SchematicRunnable {

    void apply(Block block, Pos pos);

}
