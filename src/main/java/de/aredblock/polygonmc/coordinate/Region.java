package de.aredblock.polygonmc.coordinate;

import net.minestom.server.coordinate.Pos;

public final class Region {

    private final Pos pos1;
    private final Pos pos2;

    public Region(Pos pos1, Pos pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public boolean overlap(Region region){
        return isInRegion(region.pos1) || isInRegion(region.pos2);
    }

    public boolean isInRegion(Pos pos) {
        return pos.x() >= this.pos1.x() && pos.y() >= this.pos1.y() && pos.z() >= this.pos1.z()
                && this.pos2.x() >= pos.x() && this.pos2.y() >= pos.y() && this.pos2.z() >= pos.z();
    }

}
