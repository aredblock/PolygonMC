package de.aredblock.polygonmc.vanilla.schematic;

import net.kyori.adventure.nbt.BinaryTagIO;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.nbt.IntBinaryTag;
import net.minestom.server.command.builder.arguments.minecraft.ArgumentBlockState;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.validate.Check;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public final class Schematic {

    private static final BinaryTagIO.Reader NBT_READER = BinaryTagIO.unlimitedReader();
    private final CompoundBinaryTag tag;

    private SchematicOptions options = new SchematicOptions(false);

    private CompoundBinaryTag palette;

    private int width;
    private int height;
    private int length;

    private Vec offset;

    private byte[] blockArray;

    private Block[] blocks;

    public Schematic(File file) throws Exception {
        this.tag = loadFromFile(file);

        loadFields();
    }

    public void paste(Instance instance, Pos pos){
        var placementBlocks = new ArrayList<PlacementBlock>();

        int x = 0;
        int y = 0;
        int z = 0;

        for (byte targetByte : blockArray) {
            var block = blocks[targetByte];
            var blockPosition = new Vec(x, y, z).add(offset);

            placementBlocks.add(new PlacementBlock(blockPosition.asPosition(), block));

            if (++z >= length) {
                z = 0;
                if (++x >= width) {
                    x = 0;
                    ++y;
                }
            }
        }

        placementBlocks.forEach(placementBlock -> {
            var placementPos = placementBlock.pos().add(pos);

            if(options.isAirPlacement() && placementBlock.block().isAir()){
                return;
            }

            instance.setBlock(placementPos, placementBlock.block());
        });
    }

    private void loadFields(){
        width = tag.getShort("Width");
        height = tag.getShort("Height");
        length = tag.getShort("Length");

        var metadata = tag.getCompound("Metadata");

        offset = Vec.ZERO;
        if (metadata.keySet().contains("WEOffsetX")) {
            int offsetX = metadata.getInt("WEOffsetX");
            int offsetY = metadata.getInt("WEOffsetY");
            int offsetZ = metadata.getInt("WEOffsetZ");

            offset = new Vec(offsetX, offsetY, offsetZ);
        }

        palette = tag.getCompound("Palette");
        Check.notNull(palette, "Missing required field 'Palette'");
        blockArray = tag.getByteArray("BlockData");
        Check.notNull(blockArray, "Missing required field 'BlockData'");
        int paletteSize = tag.getInt("PaletteMax");
        Check.notNull(paletteSize, "Missing required field 'PaletteMax'");

        blocks = new Block[paletteSize];

        palette.forEach((entry) -> {
            var assigned = ((IntBinaryTag) entry.getValue()).value();
            var block = ArgumentBlockState.staticParse(entry.getKey());

            blocks[assigned] = block;
        });
    }

    private CompoundBinaryTag loadFromFile(File file) throws Exception {
        var targetStream = new FileInputStream(file);
        return NBT_READER.read(targetStream, BinaryTagIO.Compression.GZIP);
    }

    public Schematic updateOption(SchematicOption option, Object value){
        if(option == SchematicOption.AIRPLACEMENT){
            options.setAirPlacement((Boolean) value);
        }

        return this;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLength() {
        return length;
    }

}
