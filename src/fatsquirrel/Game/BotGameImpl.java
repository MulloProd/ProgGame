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

    private MasterSquirrel masterSquirrel;
    private State state;
    private MoveCommand moveCommand;
    private UI ui;

    public BotGameImpl(State state, UI ui) {
        super(state,ui);
        this.ui = ui;

        for(int x=0;x<state.flattenedBoard().getSize().X;x++){
            for(int y=0;y<state.flattenedBoard().getSize().Y;y++){
                if(state.flattenedBoard().getEntityType(x,y) == EntityType.MasterSquirrelBot)
                    masterSquirrel = (MasterSquirrel)state.flattenedBoard().getEntityAt(x,y);
            }
        }
        if(masterSquirrel == null)
        {
            System.out.println("Wrong board config! No MasterSquirrelBot found!");
            System.exit(0);
        }
    }

    @Override
    public void processInput() throws IOException {

    }

    @Override
    public void render() {
        ui.render(getState().flattenedBoard());
    }

    private void spawnMiniSquirrel() throws NotEnoughEnergyException {
        if(moveCommand.getMiniSquirrelEnergy()< masterSquirrel.getEnergy()) {
            //Abfrage fehlt, ob Position frei
            int x = masterSquirrel.getPosition().X+1;
            int y = masterSquirrel.getPosition().Y+1;

            state.flattenedBoard().spawn_Mini(new XY(x,y), moveCommand.getMiniSquirrelEnergy(), masterSquirrel);
        }
        else {
            throw new NotEnoughEnergyException("Mastersquirrel besitzt nicht genügend Energie.");
        }
    }
}