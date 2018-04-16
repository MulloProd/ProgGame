package fatsquirrel.core;

import fatsquirrel.XY;

public class BoardConfig {
    private static final XY size = new XY(50, 20);
    private static final int wallCount = 136;
    private static final int beastCount = 4;
    private static final int plantCount = 4;

    //später aus Datei laden
    public static XY getSize(){
        return size;
    }

    //ebenfalls später aus datei laden
    public static int getWallCount(){
        return wallCount;
    }

    public static int getBeastCount(){return beastCount;}
    public static int getPlantCount(){return plantCount;}
}
