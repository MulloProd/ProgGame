package fatsquirrel.Game;

import fatsquirrel.Logging;
import fatsquirrel.State;
import fatsquirrel.UIs.UI;
import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.Highscore;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Game {

    private final State state;
    private UI ui;
    private final int FPS = 1;
    protected Logging logging = new Logging(this.getClass().getName());
    private Highscore highscore = new Highscore();

    public Game(State state, UI ui){
        this.state = state;
        this.ui = ui;
    }

    public void run(boolean newVersion) throws IOException, InterruptedException {
        if(newVersion){
            Timer renderTimer = new Timer();
            Timer processTimer = new Timer();

            highscore.loadHighscores();

            //1. Thread fürs Rendern
            renderTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                int round = 0;
                while(true) {
                    for (int i = 0; i < BoardConfig.getRounds(); i++) {
                        try {
                            update();
                            render();
                            Thread.sleep(1000 / FPS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    round++;
                    highscore = state.resetState(round, highscore);
                }
                }
            }, 1000);

            //2. Thread für den Input
            processTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                while(true) {
                    for (int i = 0; i < BoardConfig.getRounds(); i++) {
                        try {
                            processInput();
                            Thread.sleep(1000 / FPS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                }
            }, 1000);
        }else{
            while(true) {
                for (int i = 0; i < BoardConfig.getRounds(); i++) {
                    render();
                    processInput();
                    update();
                }
            }
        }

        //Wegspeichern beim Schließen der Anwendung
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                highscore.saveHighscores();
            }
        }, "Shutdown-thread"));
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
