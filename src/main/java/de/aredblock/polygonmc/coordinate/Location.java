package de.aredblock.polygonmc.coordinate;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.Instance;

public final class Location {

    private Pos position;
    private Instance instance;

    private Location(Pos position, Instance instance) {
        this.position = position;
        this.instance = instance;
    }

    public static Location of(Pos position, Instance instance) {
        return new Location(position, instance);
    }

    public Location withInstance(Instance instance) {
        this.instance = instance;
        return this;
    }

    public Location withPosition(Pos position) {
        this.position = position;
        return this;
    }

    @Override
    public String toString() {
        return getX() + " " + getY() + " " + getZ() + " " + instance.getUniqueId();
    }

    public Instance getInstance() {
        return instance;
    }

    public Pos getPosition() {
        return position;
    }

    public double getX(){
        return position.x();
    }

    public double getZ(){
        return position.z();
    }

    public double getY(){
        return position.y();
    }

}
