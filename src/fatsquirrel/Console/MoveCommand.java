package fatsquirrel.Console;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.Entity;

public class MoveCommand {
    private int x;
    private int y;
    private int miniEnergy;

    public MoveCommand(int x, int y, int miniEnergy){
        this.x = x;
        this.y = y;
        this.miniEnergy = miniEnergy;
    }

    public XY getDirection(){
        return new XY(x,y);
    }

    public int getMiniEnergy(){
        return (miniEnergy>0)? miniEnergy:  null;
    }
}
