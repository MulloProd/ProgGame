package fatsquirrel.core.Entities;

import fatsquirrel.Console.MoveCommand;
import fatsquirrel.XY;

import java.io.IOException;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private XY position;

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
    }

    public void nextStep(EntityContext entityContext) {

        //Testweise nach rechts laufen lassen
        if(collisionCounter==0){
            XY xy = entityContext.getState().getLastInputVector();
            entityContext.tryMove(this, xy);
        }
        else{
            collisionCounter--;
        }
    }

    private void move(int x, int y){
        setPosition(getPosition().add(new XY(x,y)));
    }

    private static void toString(String direction){
        System.out.println("\nMastersquirrel turns: " + direction + "\n");
    }

}
