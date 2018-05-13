package fatsquirrel;

import fatsquirrel.Console.BotGameImpl;
import fatsquirrel.Console.GameImpl;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Game;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Launcher {

    public static void main(String[] args) throws Exception {
        Board board = new Board();
        State state = new State(board);
        board.setState(state);
        Game game = new BotGameImpl(state);

        //Durch if ersetzen und mit Programmparameter starten
        startGame(game);
        /*
        if(args[0].toLowerCase().equals("old"))
            startOldGame(game);
        else
            startGame(game);
            */
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

        try {
            while(true){
                game.processInput();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startOldGame(Game game) throws IOException, InterruptedException {
        game.run(false);
    }

}

