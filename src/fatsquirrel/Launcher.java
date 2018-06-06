package fatsquirrel;

import fatsquirrel.Game.BotGameImpl;
import fatsquirrel.Game.GameMode;
import fatsquirrel.UIs.ConsoleUI;
import fatsquirrel.Game.GameImpl;
import fatsquirrel.UIs.FxUI;
import fatsquirrel.core.Board;
import fatsquirrel.core.BoardConfig;
import fatsquirrel.Game.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application{

    private static Board board;
    private static Game game;
    private static State state;
    private static final GameMode gameMode = GameMode.BOT;
    private static Logging logging = new Logging(Launcher.class.getName());

    public static void main(String[] args) throws Exception {

        BoardConfig.loadConfig();

        board = new Board();
        state = new State(board);

        if(gameMode.equals(gameMode.BOT) || gameMode.equals(gameMode.SOLO))
            Application.launch(args);
        else if (gameMode.equals(gameMode.CONSOLE)) {
            game = new GameImpl(new State(board), new ConsoleUI());
            startGame(game);
            logging.getLogger().info("Console game started!");
        }
        else if (gameMode.equals(gameMode.OLD)) {
            game = new GameImpl(new State(board), new ConsoleUI());
            startOldGame(game);
            logging.getLogger().info("Old console game started!");
        }
    }

    public static void startGame(Game game) {
        try {
            game.run(true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public static void startOldGame(Game game) throws IOException, InterruptedException {
        game.run(false);
    }

    public void start(Stage primaryStage) throws Exception{

        FxUI fxUI = FxUI.createInstance(BoardConfig.getSize());


        if(gameMode == GameMode.BOT) {
            game = new BotGameImpl(new State(board), fxUI);
            logging.getLogger().info("Bot JavaFX game started!");
        }
        else {
            game = new GameImpl(new State(board), fxUI);
            logging.getLogger().info("Hand operated JavaFX game started!");
        }

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Lässiges Squirrel");
        primaryStage.getIcons().add(new Image(Launcher.class.getResourceAsStream("Images\\squirrel.jpg")));
        fxUI.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent evt) {
                System.exit(-1);
            }
        });
        primaryStage.show();

        startGame(game);
    }

}

