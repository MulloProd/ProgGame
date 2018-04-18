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

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
        
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {

    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        return null;
    }

    @Override
    public void kill(Entity entity) {

    }

    @Override
    public void killAndReplace(Entity entity) {

    }

    @Override
    public EntityType getEntityType(XY xy) {
        if(entities[xy.X][xy.Y] != null)
            return new EntityType(entities[xy.X][xy.Y].getClass());
        else
            return new EntityType(null);
    }

    //Kollision
}
