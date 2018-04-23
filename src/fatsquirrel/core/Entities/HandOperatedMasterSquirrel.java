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

        if(getCollisionCounter()==0){
            XY xy = entityContext.getState().getLastInputVector();
            entityContext.tryMove(this, xy);
        }
        else{
            setCollisionCounter(-1);
        }
    }
}
