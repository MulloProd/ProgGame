package fatsquirrel.botapi;

public interface BotControllerFactory {
    BotController createMasterBotController();
    BotController createMiniBotController();
}
