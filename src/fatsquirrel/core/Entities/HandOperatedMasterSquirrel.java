package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
    private XY nextPos;

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public void nextStep(EntityContext entityContext) {
        if(getCollisionCounter()==0){
            entityContext.tryMove(this, nextPos);
        }
        else{
            setCollisionCounter(-1);
        }
    }

    public void setNextPos(XY nextPos) {
        this.nextPos = nextPos;
    }
}
