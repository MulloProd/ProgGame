package fatsquirrel.core.Entities;

import fatsquirrel.XY;


public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private XY position;
    private XY nextPos;
    private boolean spawnMiniSquirrel = false;

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
    }

    public void nextStep(EntityContext entityContext) {
        if(getCollisionCounter()==0){
            entityContext.tryMove(this, nextPos);

            if(spawnMiniSquirrel){}
            //MiniSquirrel setzen
        }
        else{
            setCollisionCounter(-1);
        }
    }


    public void setSpawnMiniSquirrel(int energy) {
        ;
    }

    public void setNextPos(XY nextPos) {
        this.nextPos = nextPos;
    }
}
