package fatsquirrel.Console;

import fatsquirrel.XY;

public class MoveCommand {
    private int x;
    private int y;
    private int miniSquirrelEnergy;
    private boolean listAll;
    private boolean listMasterSquirrelEnergy;

    public MoveCommand(int x, int y, int miniSquirrelEnergy, boolean listAll, boolean listMasterSquirrelEnergy){
        this.x = x;
        this.y = y;
        this.miniSquirrelEnergy = miniSquirrelEnergy;
        this.listAll = listAll;
        this.listMasterSquirrelEnergy = listMasterSquirrelEnergy;
    }

    public XY getDirection(){
        return new XY(x,y);
    }

    public int getMiniSquirrelEnergy(){
        return miniSquirrelEnergy;
    }

    public boolean getListAll() {
        return listAll;
    }

    public boolean getListMasterSquirrelEnergy() {
        return listMasterSquirrelEnergy;
    }
}
