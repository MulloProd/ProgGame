package fatsquirrel.Console;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.Entity;

public class MoveCommand {
    private int x;
    private int y;

    public MoveCommand(int x, int y){
        this.x = x;
        this.y = y;
    }

    public XY getDirection(){
        return new XY(x,y);
    }
}
