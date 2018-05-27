package fatsquirrel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.*;

public class Logging {
    private Logger logger;
    private String standardPath = "./logs/";

    public Logging(String name, String folderName) {
        Init(name, standardPath + folderName + "/");
    }

    public Logging(String name){
        Init(name, standardPath);
    }

    private void Init(String name, String path){
        logger = Logger.getLogger(name);
        Handler handler = null;
        try {
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

            handler = new FileHandler(path + logger.getName() + ".html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.setFormatter(new LoggingFormatter());
        logger.addHandler(handler);
        logger.setLevel(Level.FINE);

    }

    public Logger getLogger() {
        return logger;
    }
}
