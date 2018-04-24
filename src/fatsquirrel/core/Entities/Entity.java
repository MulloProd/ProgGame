package fatsquirrel.core.Entities;

import fatsquirrel.XY;

import java.io.IOException;

public abstract class Entity {
    private final int ID;
    private int energy;
    protected final int startEnergy;
    private XY position;

    private int collisionCounter = 0;
    private int nextStepCounter = 0;

    public Entity(int id, int energy, XY position) {
        ID = id;
        startEnergy = energy;
        this.energy = energy;
        this.position = position;
    }

    public abstract void nextStep(EntityContext entityContext) throws IOException;

    public XY getPosition() {
        return position;
    }

    public void setPosition(XY position){
        this.position = position;
    }

    public int getStartEnergy() {
        return startEnergy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getID() {
        return ID;

    }

    public int updateEnergy(int value){
        if(value==0){
            energy=0;
            return 0;
        }
        else if(energy + value <= startEnergy) {
            energy += value;
            return 0;
        }
        else{
            int difference = energy + value - startEnergy;
            energy = startEnergy;
            return difference;
        }
    }

    public String toString(){
        return this.getClass().getSimpleName() + "(" + ID + ")" + ", Energy: " + energy + "/" + startEnergy + ", Pos: " + position.X + "/" + position.Y;
    }

    public int getCollisionCounter() {
        return collisionCounter;
    }

    public void setCollisionCounter(int collisionCounter) {
        this.collisionCounter = this.collisionCounter+collisionCounter;
    }

    public int getNextStepCounter() {
        return nextStepCounter;
    }

    public void setNextStepCounter(int nextStepCounter) {
        this.nextStepCounter = this.nextStepCounter+nextStepCounter;
    }
}
