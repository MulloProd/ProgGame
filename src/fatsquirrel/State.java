package fatsquirrel;

import fatsquirrel.core.Board;
import fatsquirrel.core.FlattenedBoard;

import java.io.IOException;

public class State {
    private int Highscore;
    private Board board;

    public State(Board board){
        this.board = board;
    }

    public void update() throws IOException {
        board.updateEntitySet();
    }

    public int getHighscore() {
        return Highscore;
    }

    public void setHighscore(int highscore) {
        Highscore = highscore;
    }

    public FlattenedBoard flattenedBoard(){
        return board.flatten();
    }
}
