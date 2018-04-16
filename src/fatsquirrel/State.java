package fatsquirrel;

import fatsquirrel.core.Board;

public class State {
    private int Highscore;
    Board board;

    public State(Board board){
        this.board = board;
    }

    public void update(){

    }

    public int getHighscore() {
        return Highscore;
    }

    public void setHighscore(int highscore) {
        Highscore = highscore;
    }
}
