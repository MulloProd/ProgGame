package fatsquirrel.Console;

import fatsquirrel.State;
import fatsquirrel.UI;
import fatsquirrel.core.BoardView;
import fatsquirrel.core.Game;

public class GameImpl extends Game {
    public GameImpl(State state) {
        super(state);
    }

    @Override
    public void processInput() {

    }

    @Override
    public void render() {
        UI ui = new ConsoleUI();
        ui.render(getState().flattenedBoard());
    }
}
