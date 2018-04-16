package fatsquirrel.core;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.*;

public class FlattenedBoard implements EntityContext, BoardView {

    private final Entity[][] entities;
    private final int width;
    private final int height;

    public FlattenedBoard(Entity[][] entities, int width, int height){
        this.entities = entities;
        this.width = width;
        this.height = height;
    }

    @Override
    public EntityType getEntityType(int x, int y) {
        if(entities[x][y] != null)
            return new EntityType(entities[x][y].getClass());
        else
            return new EntityType(null);
    }

    @Override
    public XY getSize() {
        return new XY(width, height);
    }
}
