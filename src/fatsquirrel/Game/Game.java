package fatsquirrel.Game;

import fatsquirrel.Logging;
import fatsquirrel.State;
import fatsquirrel.UIs.UI;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Game {

    private final State state;
    private UI ui;
    private final int FPS = 1;
    protected Logging logging = new Logging(this.getClass().getName());

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
                            render();
                            update();
                            Thread.sleep(1000/FPS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }, 1000);

            //2. Thread für den Input
            processTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    while(true){
                        try {
                            processInput();
                            Thread.sleep(1000/FPS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }, 1000);

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
        logging.getLogger().fine("Game updated");
    }

    public abstract void processInput() throws IOException;

    public abstract void render();

    public State getState() {
        return state;
    }
}
