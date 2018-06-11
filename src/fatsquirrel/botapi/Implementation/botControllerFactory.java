package fatsquirrel.botapi.Implementation;

import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.BotControllerFactory;
import fatsquirrel.core.BoardConfig;

import java.util.logging.Logger;

public class botControllerFactory implements BotControllerFactory {
    @Override
    public BotController createMasterBotController() {
        Class cl = null;
        try {
            cl = Class.forName("fatsquirrel.botimpls.Gruppe7"+ BoardConfig.getNextBotName()+"MasterBotController");
        } catch (ClassNotFoundException e) {
            try {
                cl = Class.forName("fatsquirrel.botimpls.randomMasterBotController");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
        if(cl != null) {
            try {
                return (BotController) cl.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public BotController createMiniBotController() {
        return new randomMiniBotController();
    }
}
