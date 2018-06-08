package fatsquirrel.Game;

import fatsquirrel.Logging;
import fatsquirrel.State;
import fatsquirrel.UIs.UI;
import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.Highscore;

import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

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

            //Import vom Highscore
            FileReader fileReader = new FileReader("file.txt");
            String importedHighscore = new BufferedReader(fileReader).readLine();
            fileReader.close();


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
                    highscore = state.resetState(round, importedHighscore);
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
                try {
                    FileWriter fileWriter = new FileWriter("file.txt");
                    fileWriter.write(highscore.toString());
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
