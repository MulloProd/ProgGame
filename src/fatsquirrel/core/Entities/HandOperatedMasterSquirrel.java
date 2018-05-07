package fatsquirrel.core.Entities;

import fatsquirrel.core.NotEnoughEnergyException;
import fatsquirrel.XY;


public class HandOperatedMasterSquirrel extends MasterSquirrel {

    private XY position;
    private XY nextPos;

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
    }

    public void nextStep(EntityContext entityContext) {
        if(getCollisionCounter()==0){
            entityContext.tryMove(this, nextPos);

        }
        else{
            setCollisionCounter(-1);
        }
    }


    public void spawnMiniSquirrel(int energy) throws NotEnoughEnergyException {
        if(energy > this.getEnergy()){
            throw new NotEnoughEnergyException("Mastersquirrel bestitzt nicht genug Energie.");
        }
        else{
            //Spwanen
        }
    }

    public void setNextPos(XY nextPos) {
        this.nextPos = nextPos;
    }
}
