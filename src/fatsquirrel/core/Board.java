package fatsquirrel.core;

import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.*;

public class Board {
    private EntitySet entitySet;
    private final int height;
    private final int width;
    private Entity[][] entities;

    public Board(EntitySet entitySet){
        this.entitySet = entitySet;
        height = BoardConfig.size.Y;
        width = BoardConfig.size.X;
        CreateRandomBoard();
    }

    private void CreateRandomBoard(){

    }
}
