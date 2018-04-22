package fatsquirrel.core.Entities;

import fatsquirrel.Console.MoveCommand;
import fatsquirrel.XY;

import java.io.IOException;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    XY position;

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
    }

    public void nextStep(EntityContext entityContext) {

        //Testweise nach rechts laufen lassen
        XY xy = new XY (1,0);
        entityContext.tryMove(this, xy);

    }

    private void move(int x, int y){
        setPosition(getPosition().add(new XY(x,y)));
    }

    private static void toString(String direction){
        System.out.println("\nMastersquirrel turns: " + direction + "\n");
    }

}
