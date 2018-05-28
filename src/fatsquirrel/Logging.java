package fatsquirrel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;

public class Logging {
    private Logger logger;
    private String standardPath = "./logs/";
    private Level standardLevel = Level.SEVERE;

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
            deleteDir(new File("./logs"));
            File dir = new File("./logs");
            dir.mkdir();
            dir = new File("./logs/MiniBots");
            dir.mkdir();
            dir = new File("./logs/MasterBots");
            dir.mkdir();
            dir = new File("./logs/Entities");
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

    public void closeLogger(){
        for(Handler handler: getLogger().getHandlers())
            handler.close();
    }

    void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }

    public Logger getLogger() {
        return logger;
    }
}
