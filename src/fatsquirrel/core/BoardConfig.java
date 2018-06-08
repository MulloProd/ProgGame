package fatsquirrel.core;

import fatsquirrel.XY;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class BoardConfig {

    //Wird per Config-File gesetzt
    private static String[] botNames={""};
    private static XY size = new XY(0,0);
    private static int rounds = 10;
    private static int wallCount= size.x *2 + (size.y -2)*2 + 10;
    private static int beastCount = 2;
    private static int plantCount = 2;
    private static int handOperatedMasterSquirrelCount = 0;
    private static int botMasterSquirrelCount =1;

    public static XY getSize(){return size;}
    public static int getWallCount(){return wallCount;}
    public static int getHandOperatedMasterSquirrelCount(){return handOperatedMasterSquirrelCount;}
    public static int getbotMasterSquirrelCount(){return botMasterSquirrelCount;}
    public static int getBeastCount(){return beastCount;}
    public static int getPlantCount(){return plantCount;}
    public static String[] getBotNames(){
        return botNames;
    }
    public static int getRounds() {
        return rounds;
    }

    public static void loadConfig() throws IOException {
        File propertiesFile = new File("./src/fatsquirrel/config.properties");
        Properties properties = new Properties();

        if(propertiesFile.exists()) {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(propertiesFile));
            properties.load(bis);
            bis.close();
        }

        //Werte aus Config-File parsen
        if(Integer.parseInt(properties.getProperty("rounds"))>0)
            rounds=Integer.parseInt(properties.getProperty("rounds"));
        if(Integer.parseInt(properties.getProperty("boardSizeX"))>0 && Integer.parseInt(properties.getProperty("boardSizeY"))>0)
            size = new XY(Integer.parseInt(properties.getProperty("boardSizeX")), Integer.parseInt(properties.getProperty("boardSizeY")));
        if(Integer.parseInt(properties.getProperty("wallCount"))>=0)
            wallCount= size.x *2 + (size.y -2)*2 + Integer.parseInt(properties.getProperty("wallCount"));
        if(Integer.parseInt(properties.getProperty("beastCount"))>=0)
            beastCount=Integer.parseInt(properties.getProperty("beastCount"));
        if(Integer.parseInt(properties.getProperty("plantCount"))>=0)
            plantCount=Integer.parseInt(properties.getProperty("plantCount"));
        if(Integer.parseInt(properties.getProperty("handOperatedMasterSquirrelCount"))>=0)
            handOperatedMasterSquirrelCount=Integer.parseInt(properties.getProperty("handOperatedMasterSquirrelCount"));
        if(Integer.parseInt(properties.getProperty("botMasterSquirrelCount"))>=0)
            botMasterSquirrelCount=Integer.parseInt(properties.getProperty("botMasterSquirrelCount"));
        if(properties.getProperty("botNames").length()>0)
            botNames = properties.getProperty("botNames").split(",");
    }

}
