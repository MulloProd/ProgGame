package fatsquirrel.Console;

import fatsquirrel.State;
import fatsquirrel.UI;
import fatsquirrel.core.BoardView;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.HandOperatedMasterSquirrel;
import fatsquirrel.core.Entities.Wall;
import fatsquirrel.core.Game;

import java.io.IOException;
import java.util.function.Consumer;

public class GameImpl extends Game {

    ConsoleUI consoleUI = new ConsoleUI();

    public GameImpl(State state) {
        super(state);
    }

    @Override
    public void processInput() throws IOException {
        getState().setLastInputVector(consoleUI.getCommand().getDirection());

        //Befehl zum Erzeugen von MiniSquirrels
    }

    @Override
    public void render() {
        consoleUI.render(getState().flattenedBoard());
    }
}