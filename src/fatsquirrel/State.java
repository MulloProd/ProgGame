package fatsquirrel;

import fatsquirrel.core.Board;
import fatsquirrel.core.FlattenedBoard;

import java.io.IOException;

public class State {
    private int highscore;
    private Board board;
    private XY lastInputVector = new XY(0,0);

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

    public XY getLastInputVector() {
        return lastInputVector;
    }

    public void setLastInputVector(XY lastInputVector) {
        this.lastInputVector = lastInputVector;
    }
}
