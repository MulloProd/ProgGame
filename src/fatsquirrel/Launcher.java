package fatsquirrel;

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
        Game game = new GameImpl(state);
        startGame(game);
    }

    public static void startGame(Game game) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    game.run();
                    game.processInput();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }, 1000);
    }
}

