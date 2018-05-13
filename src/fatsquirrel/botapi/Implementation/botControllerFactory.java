package fatsquirrel.botapi.Implementation;

import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.BotControllerFactory;

public class botControllerFactory implements BotControllerFactory {
    @Override
    public BotController createMasterBotController() {
        return new masterBotController();
    }

    @Override
    public BotController createMiniBotController() {
        return new miniBotController();
    }
}
