package fatsquirrel.Game;

import fatsquirrel.Console.MoveCommand;
import fatsquirrel.State;
import fatsquirrel.UIs.UI;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Entities.PlayerEntities.HandOperatedMasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.NotEnoughEnergyException;

import java.io.IOException;

public class GameImpl extends Game {

    private UI ui;
    private MasterSquirrel masterSquirrel;
    private MoveCommand moveCommand;

    public GameImpl (State state, UI ui){
        super(state, ui);
        this.ui = ui;

        for(int x=0;x<state.flattenedBoard().getSize().X;x++){
            for(int y=0;y<state.flattenedBoard().getSize().Y;y++){
                if(state.flattenedBoard().getEntityType(x,y) == EntityType.HandOperatedMasterSquirrel)
                    masterSquirrel = (MasterSquirrel)state.flattenedBoard().getEntityAt(x,y);
            }
        }
        if(masterSquirrel == null)
        {
            System.out.println("Wrong board config! No HandOperatedMasterSquirrel found!");
            System.exit(0);
        }
    }

    @Override
    public void processInput() throws IOException {
        //Abfrage Eingabe
        moveCommand = ui.getCommand();
        if(moveCommand != null) {
            //Position neu setzen, falls Eingabe war Bewegung
            ((HandOperatedMasterSquirrel)masterSquirrel).setNextMoveDirection(moveCommand.getDirection());

            //Auflisten aller Entities mit Energie -> Problem Board und Entities werden neu erstellt (Bewegung fährt fort)
            if (moveCommand.getListAll()) {
                System.out.println(getState().flattenedBoard().allEntitiesToString());
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
        ui.render(getState().flattenedBoard());
    }

    private void spawnMiniSquirrel() throws NotEnoughEnergyException {
        if(moveCommand.getMiniSquirrelEnergy()< masterSquirrel.getEnergy()) {
            //Abfrage fehlt, ob Position frei
            int x = masterSquirrel.getPosition().X+1;
            int y = masterSquirrel.getPosition().Y+1;

            getState().flattenedBoard().spawn_Mini(new XY(1,1), moveCommand.getMiniSquirrelEnergy(), masterSquirrel);
        }
        else {
            throw new NotEnoughEnergyException("Mastersquirrel besitzt nicht genügend Energie.");
        }
    }
}