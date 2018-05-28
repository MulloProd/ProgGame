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

        for(int x = 0; x<state.flattenedBoard().getSize().x; x++){
            for(int y = 0; y<state.flattenedBoard().getSize().y; y++){
                if(state.flattenedBoard().getEntityType(x,y) == EntityType.MASTER_SQUIRREL)
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

        logging.getLogger().info("Input processed");
    }

    @Override
    public void render() {
        ui.render(getState().flattenedBoard());
        logging.getLogger().info("GUI updated");
    }

    private void spawnMiniSquirrel() throws NotEnoughEnergyException {
        if(moveCommand.getMiniSquirrelEnergy()< masterSquirrel.getEnergy()) {
            //Abfrage fehlt, ob Position frei
            int x = masterSquirrel.getPosition().x +1;
            int y = masterSquirrel.getPosition().y +1;

            getState().flattenedBoard().spawn_Mini(new XY(1,1), moveCommand.getMiniSquirrelEnergy(), masterSquirrel);
        }
        else {
            logging.getLogger().warning("MasterSquirrel#" + masterSquirrel.getID() + " don´t have enough energy!");
            throw new NotEnoughEnergyException("Mastersquirrel besitzt nicht genügend Energie.");
        }
    }
}