package fatsquirrel.core.Entities;

import fatsquirrel.core.Entities.Entity;

import java.io.IOException;

public class EntitySet {

    public static Entity[] set;
    private int length;

    public EntitySet (int length){
        set = new Entity[length];
        this.length = length;
    }
    public void addEntity(Entity newEntity){
        for(int i = 0; i< length; i++){
            if(set[i] == null){
                set[i] = newEntity;
                break;
            }
        }
    }

    public void deleteEntity(Entity oldEntity){
        for(int i = 0; i<length; i++) {
            if (i == oldEntity.getID()) {
                set[i] = null;
                break;
            }
        }
    }

    public void nextStep() throws IOException {
        for(Entity e : set){
            if(e!=null)
                e.nextStep();
        }
    }

    public String toString(){
        String allEntities = "";

        for(int i = 0; i<length; i++){
            if (set[i] != null) {
                allEntities += set[i].toString() + "\n";
            }
        }

        return allEntities;
    }
}
