package fatsquirrel.Game;

import fatsquirrel.Console.MoveCommand;
import fatsquirrel.State;
import fatsquirrel.UIs.UI;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.NotEnoughEnergyException;

import java.io.IOException;

public class BotGameImpl extends Game {

    private MoveCommand moveCommand;
    private UI ui;

    public BotGameImpl(State state, UI ui) {
        super(state,ui);
        this.ui = ui;
    }

    @Override
    public void processInput() throws IOException {

    }

    @Override
    public void render() {
        ui.render(getState().flattenedBoard());
    }
}