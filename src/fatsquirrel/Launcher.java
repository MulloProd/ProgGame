package fatsquirrel;

import fatsquirrel.Console.GameImpl;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Game;

import java.io.IOException;
import java.util.Random;

public class Launcher {

    public static void main(String[] args) throws Exception {
        Board board = new Board();
        State state = new State(board);
        board.setState(state);
        Game game = new GameImpl(state);
        game.run();
    }
}

