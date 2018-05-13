package fatsquirrel.core;

import fatsquirrel.State;

import java.io.IOException;

public abstract class Game {

    private final State state;
    private final int FPS = 1;

    public Game(State state){
        this.state = state;
    }

    public void run(boolean newVersion) throws IOException, InterruptedException {
        if(newVersion){
            while(true){
                Thread.sleep(1000/FPS);
                render();
                update();
            }
        }else{
            while (true){
                render();
                processInput();
                update();
            }
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
