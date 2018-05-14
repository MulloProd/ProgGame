package fatsquirrel;

import fatsquirrel.Console.BotGameImpl;
import fatsquirrel.Console.ConsoleUI;
import fatsquirrel.Console.GameImpl;
import fatsquirrel.core.Board;
import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application{

    private static Board board;
    private static Game game;
    private static State state;
    private static final GameMode gameMode = GameMode.JFX;

    public static void main(String[] args) throws Exception {
        board = new Board();
        state = new State(board);
        board.setState(state);
        //Game game = new BotGameImpl(state);


        if(gameMode.equals(gameMode.JFX))
            Application.launch(args);
        else if (gameMode.equals(gameMode.CONSOLE)) {
            game = new GameImpl(new State(board), new ConsoleUI());
            startGame(game);
        }
        else if (gameMode.equals(gameMode.OLD))
            startOldGame(game);
    }

    public static void startGame(Game game) {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    game.run(true);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, 1000);
    }

    public static void startOldGame(Game game) throws IOException, InterruptedException {
        game.run(false);
    }

    public void start(Stage primaryStage) throws Exception{

        FxUI fxUI = FxUI.createInstance(BoardConfig.getSize());

        game = new GameImpl(new State(board), fxUI);

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Lästiges Squirrel");
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

