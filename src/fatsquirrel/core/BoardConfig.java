package fatsquirrel.core;

import fatsquirrel.XY;

public class BoardConfig {
    private static final XY size = new XY(50, 15);
    private static final int wallCount = size.X*2 + (size.Y-2)*2;
    private static final int beastCount = 4;
    private static final int plantCount = 4;
    private static final int masterSquirrelCount = 1;

    //später aus Datei laden
    public static XY getSize(){return size;}

    //ebenfalls später aus Datei laden
    public static int getWallCount(){return wallCount;}

    public static int getBeastCount(){return beastCount;}
    public static int getPlantCount(){return plantCount;}

    public static int getMasterSquirrelCount() {return masterSquirrelCount;}
}