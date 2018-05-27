package fatsquirrel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.*;

public class Logging {
    private Logger logger;
    private String standardPath = "./logs/";
    private Level standardLevel = Level.FINE;

    public Logging(String name, String folderName, Level level) {
        Init(name, standardPath + folderName + "/", level);
    }

    public Logging(String name, Level level){
        Init(name, standardPath, level);
    }

    public Logging(String name, String folderName) {
        Init(name, standardPath + folderName + "/", standardLevel);
    }

    public Logging(String name){
        Init(name, standardPath, standardLevel);
    }

    private void Init(String name, String path, Level level){

        logger = Logger.getLogger(name);

        //Handler erstellen
        Handler handler = null;
        try {
            //Alte  Logs löschen
            File[] allContents = new File("./logs").listFiles();
            if (allContents != null) {
                for (File file : allContents) {
                    file.delete();
                }
            }

            File dir = new File("./logs/MiniBots");
            dir.mkdir();
            dir = new File("./logs/MasterBots");
            dir.mkdir();

            //Handler erstellen
            handler = new FileHandler(path + logger.getName() + ".html");
            handler.setFormatter(new LoggingFormatter());
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.addHandler(handler);
        logger.setLevel(level);
    }

    public Logger getLogger() {
        return logger;
    }
}
