package fatsquirrel.Console;

import fatsquirrel.State;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.HandOperatedMasterSquirrel;
import fatsquirrel.core.Entities.MiniSquirrel;
import fatsquirrel.core.Entities.StandardMiniSquirrel;
import fatsquirrel.core.Game;
import fatsquirrel.core.NotEnoughEnergyException;

import java.io.IOException;

public class GameImpl extends Game {

    private ConsoleUI consoleUI = new ConsoleUI();
    private HandOperatedMasterSquirrel masterSquirrel;
    private State state;
    private MoveCommand moveCommand;

    public GameImpl(State state) {
        super(state);
        this.state = state;

        for(Entity entity : state.flattenedBoard().getEntitySet().set){
            if(entity instanceof HandOperatedMasterSquirrel)
                masterSquirrel = (HandOperatedMasterSquirrel)entity;
        }
    }

    @Override
    public void processInput() throws IOException {
        //Abfrage Eingabe
        moveCommand = consoleUI.getCommand();
        if(moveCommand != null) {
            //Position neu setzen, falls Eingabe war Bewegung
            masterSquirrel.setNextPos(moveCommand.getDirection());

            //Auflisten aller Entities mit Energie -> Problem Board und Entities werden neu erstellt (Bewegung fährt fort)
            if (moveCommand.getListAll()) {
                System.out.println(state.flattenedBoard().getEntitySet().toString());
                processInput();
            }

            //Ausgabe der Mastersquirrel-Energy
            if (moveCommand.getListMasterSquirrelEnergy()) {
                System.out.println(masterSquirrel.getEnergy());
                processInput();
            }

            //Minisquirrel spawnen lassen
            if (moveCommand.getMiniSquirrelEnergy() > 0) {
                try {
                    spawnMiniSquirrel();
                } catch (NotEnoughEnergyException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void render() {
        consoleUI.render(getState().flattenedBoard());
    }

    private void spawnMiniSquirrel() throws NotEnoughEnergyException {
        if(moveCommand.getMiniSquirrelEnergy()< masterSquirrel.getEnergy()) {
            int id = state.flattenedBoard().getEntitySet().getNextFreeID();
            //Abfrage fehlt, ob Position frei
            int x = masterSquirrel.getPosition().X+1;
            int y = masterSquirrel.getPosition().Y+1;

            StandardMiniSquirrel standardMiniSquirrel = new StandardMiniSquirrel(id, moveCommand.getMiniSquirrelEnergy(), new XY(x, y), masterSquirrel);
            state.flattenedBoard().getEntitySet().addEntity(standardMiniSquirrel);
            state.flattenedBoard().setEntities(standardMiniSquirrel);
        }
        else {
            throw new NotEnoughEnergyException("Mastersquirrel besitzt nicht genügend Energie.");
        }
    }
}