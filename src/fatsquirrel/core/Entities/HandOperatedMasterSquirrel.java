package fatsquirrel.core.Entities;

import fatsquirrel.XY;


public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private XY position;
    private boolean spawnMiniSquirrel = false;

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
    }

    public void nextStep(EntityContext entityContext) {

        if(getCollisionCounter()==0){
            XY xy = entityContext.getState().getLastInputVector();
            entityContext.tryMove(this, xy);

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
}
