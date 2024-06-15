package de.aredblock.polygonmc.vanilla.schematic;

class SchematicOptions {

    private boolean airPlacement;

    public SchematicOptions(boolean airPlacement) {
        this.airPlacement = airPlacement;
    }

    void setAirPlacement(boolean airPlacement){
        this.airPlacement = airPlacement;
    }
    public boolean isAirPlacement() {
        return airPlacement;
    }

}
