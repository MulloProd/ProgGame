package fatsquirrel.core;

import fatsquirrel.XY;

import java.util.Random;

public class BoardConfig {
    private static final XY size = new XY(15, 15);

    private static final int wallCount = size.x *2 + 10 +(size.y -2)*2 + new Random().nextInt(10); //Mindestens 10 Wände extra
    private static final int beastCount = 0;
    private static final int plantCount = 0;

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
