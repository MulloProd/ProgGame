package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntityContext;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private XY nextMoveDirection = new XY (0,0);

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public void nextStep(EntityContext entityContext) {
        if(getCollisionCounter()==0){
            entityContext.tryMove(this, nextMoveDirection);
            setNextMoveDirection(new XY(0,0));
        }
        else{
            setCollisionCounter(-1);
        }
    }

    public void setNextMoveDirection(XY nextMoveDirection) {
        this.nextMoveDirection = nextMoveDirection;
    }
}
