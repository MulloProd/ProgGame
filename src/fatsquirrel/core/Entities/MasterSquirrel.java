package fatsquirrel.core.Entities;

import fatsquirrel.*;

public abstract class MasterSquirrel extends PlayerEntity{

    public MasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public boolean checkSquirrel(Entity entity){
        return entity instanceof MiniSquirrel;
    }

    public void sendEnergy(Entity entity, int miniEnergy){
        if(checkSquirrel(entity)){
            MiniSquirrel miniSquirrel = (MiniSquirrel) entity;

            int difference = miniSquirrel.updateEnergy(miniEnergy);

            if(difference>0) {
                updateEnergy(-(miniEnergy-difference));
                toString("Mastersquirrel übergibt " + (miniEnergy-difference) + " Energie an Minisquirrel.\n");
            }
            else {
                updateEnergy(-miniEnergy);
                toString("Mastersquirrel übergibt " + miniEnergy + " Energie an Minisquirrel.\n");
            }
        }
    }

    private static void toString(String string){
        System.out.println(string);
    }

}


