package fatsquirrel.core;

import fatsquirrel.State;
import fatsquirrel.UI;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Game {

    private final State state;
    private UI ui;
    private final int FPS = 1;

    public Game(State state){
        this.state = state;
    }

    public Game(State state, UI ui){
        this.state = state;
        this.ui = ui;
    }

    public void run(boolean newVersion) throws IOException, InterruptedException {

        if(newVersion){
            Timer renderTimer = new Timer();
            Timer processTimer = new Timer();

            //1. Thread fürs Rendern
            renderTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(1000/FPS);
                            render();
                            update();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }, 0);

            //2. Thread für den Input
            processTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(1000/FPS);
                            processInput();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }, 0);

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
