package fatsquirrel.core;

import fatsquirrel.State;

import java.io.IOException;

public abstract class Game {

    private final State state;

    public Game(State state){
        this.state = state;
    }

    public void run() throws IOException {
        while(true){
            render();
            processInput();
            update();
        }
    }

    private void update() throws IOException {
        state.update();
    }

    public abstract void processInput() throws IOException;

    public abstract void render();

    public State getState() {
        return state;
    }
}
