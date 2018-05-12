package fatsquirrel.core;

import fatsquirrel.State;

import java.io.IOException;

public abstract class Game {

    private final State state;
    private final int FPS = 2;

    public Game(State state){
        this.state = state;
    }

    public void run() throws IOException, InterruptedException {
        while(true){
            //Thread.sleep(2000);
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
