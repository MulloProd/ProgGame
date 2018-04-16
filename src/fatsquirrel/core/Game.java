package fatsquirrel.core;

import fatsquirrel.State;

public abstract class Game {

    private final State state;

    public Game(State state){
        this.state = state;
    }

    public void run(){
        while(true){
            render();
            processInput();
            update();
        }
    }

    private void update() {

    }

    public abstract void processInput();

    public abstract void render();

    public State getState() {
        return state;
    }
}
