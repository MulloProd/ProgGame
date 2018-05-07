package fatsquirrel.Console;

import fatsquirrel.State;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.HandOperatedMasterSquirrel;
import fatsquirrel.core.Game;
import fatsquirrel.core.NotEnoughEnergyException;

import java.io.IOException;

public class GameImpl extends Game {

    private ConsoleUI consoleUI = new ConsoleUI();
    private HandOperatedMasterSquirrel masterSquirrel;
    private State state;

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
        MoveCommand moveCommand = consoleUI.getCommand();

        //Position neu setzen, falls Eingabe war Bewegung
        masterSquirrel.setNextPos(moveCommand.getDirection());

        //Auflisten aller Entities mit Energie -> Problem Board und Entities werden neu erstellt (Bewegung fährt fort)
        if (moveCommand.getListAll()){
            System.out.println(state.flattenedBoard().getEntitySet().toString());
        }

        //Ausgabe der Mastersquirrel-Energy
        if(moveCommand.getListMasterSquirrelEnergy()){
            System.out.println(masterSquirrel.getEnergy());
        }

        //Minisquirrel spawnen lassen
        if(moveCommand.getMiniSquirrelEnergy()>0){
            try{
                masterSquirrel.spawnMiniSquirrel(moveCommand.getMiniSquirrelEnergy());
            }
            catch (NotEnoughEnergyException e){

            }
        }
    }

    @Override
    public void render() {
        consoleUI.render(getState().flattenedBoard());
    }
}