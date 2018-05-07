package fatsquirrel;

import fatsquirrel.core.Board;
import fatsquirrel.core.FlattenedBoard;

import java.io.IOException;

public class State {
    private int highscore;
    private Board board;

    public State(Board board){
        this.board = board;
    }

    public void update() throws IOException {
        board.updateEntitySet();
    }

    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public FlattenedBoard flattenedBoard(){
        return board.flatten();
    }
}
