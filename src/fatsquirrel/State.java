package fatsquirrel;

import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.FlattenedBoard;
import fatsquirrel.core.Highscore;

import java.io.IOException;
import java.util.ArrayList;

public class State {
    private Board board;
    private Highscore highscore = new Highscore();

    public State(Board board){
        this.board = board;

    }

    public void update() throws IOException {
        board.updateEntitySet();
    }

    public FlattenedBoard flattenedBoard(){
        return board.flatten();
    }

    public Highscore resetState(int round, String importedHighscore){
        ArrayList<Entity> botList = board.getBots();
        highscore.calculateHighscore(botList,round, importedHighscore);
        board.resetBoard();
        return highscore;
    }
}
