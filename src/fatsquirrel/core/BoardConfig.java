package fatsquirrel.core;

import fatsquirrel.XY;

public class BoardConfig {
    private static final XY size = new XY(15, 15);
    private static final int wallCount = size.X*2 + (size.Y-2)*2;
    private static final int beastCount = 1;
    private static final int plantCount = 3;
    private static final int handOperatedMasterSquirrelCount = 0;
    private static final int botMasterSquirrelCount = 1;

    //später aus Datei laden
    public static XY getSize(){return size;}

    //ebenfalls später aus Datei laden
    public static int getWallCount(){return wallCount;}

    public static int getHandOperatedMasterSquirrelCount(){return handOperatedMasterSquirrelCount;}
    public static int getbotMasterSquirrelCount(){return botMasterSquirrelCount;}

    public static int getBeastCount(){return beastCount;}
    public static int getPlantCount(){return plantCount;}

}
