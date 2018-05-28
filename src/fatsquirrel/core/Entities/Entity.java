package fatsquirrel.core.Entities;

import fatsquirrel.Logging;
import fatsquirrel.XY;

import java.io.IOException;

public abstract class Entity {
    private final int ID;
    private int energy;
    protected final int startEnergy;
    private XY position;

    private int collisionCounter = 0;
    private int nextStepCounter = 0;

    private Logging logging;

    public Entity(int id, int energy, XY position) {
        ID = id;
        startEnergy = energy;
        this.energy = energy;
        this.position = position;

        logging = new Logging(this.getClass().getSimpleName() + "#"+id, "Entities");
    }

    public Logging getLogging(){
        return logging;
    }

    @Override
    protected void finalize() throws Throwable {
        logging.closeLogger();
    }

    public abstract void nextStep(EntityContext entityContext) throws IOException;

    public XY getPosition() {
        logging.getLogger().finest("getPosition called");
        return position;
    }

    public void setPosition(XY position){
        logging.getLogger().finest("setPosition called");
        logging.getLogger().fine("moved to " + position.toString());
        this.position = position;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public int getEnergy() {
        logging.getLogger().finest("getEnergy called");
        return energy;
    }

    public int getID() {
        logging.getLogger().finest("getID called");
        return ID;
    }

    public int updateEnergy(int value){
        if(value==0){
            logging.getLogger().fine("Changed energy from " + energy + " to " + energy);
            return 0;
        }
        else if(energy+value <= 0){
            logging.getLogger().fine("Changed energy from " + energy + " to " + 0);
            energy=0;
            return 0;
        }
        else if(energy + value <= startEnergy) {
            logging.getLogger().fine("Changed energy from " + energy + " to " + (energy+value));
            energy += value;
            return 0;
        }
        else{
            logging.getLogger().fine("Changed energy from " + energy + " to " + startEnergy);
            int difference = energy + value - startEnergy;
            energy = startEnergy;
            return difference;
        }
    }

    public String toString(){
        return this.getClass().getSimpleName() + "(" + ID + ")" + ", Energy: " + energy + "/" + startEnergy + ", Pos: " + position.X + "/" + position.Y;
    }

    public int getCollisionCounter() {
        logging.getLogger().finest("getCollisionCounter called");
        return collisionCounter;
    }

    public void setCollisionCounter(int collisionCounter) {
        logging.getLogger().finest("setCollisionCounter called");
        this.collisionCounter = this.collisionCounter+collisionCounter;
    }

    public int getNextStepCounter() {
        logging.getLogger().finest("getNextStepCounter called");
        return nextStepCounter;
    }

    public void setNextStepCounter(int nextStepCounter) {
        logging.getLogger().finest("setNextStepCounter called");
        this.nextStepCounter = this.nextStepCounter+nextStepCounter;
    }
}
