package fatsquirrel.core;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.*;

import java.util.Random;

public class FlattenedBoard implements EntityContext, BoardView {

    private final Entity[][] entities;
    private final int width;
    private final int height;
    private int collisionCounter = 0;

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
        for(int i = 0; i < entities.length; i++){
            for(int j = 0; j < entities[0].length; j++){
                if(entities[i][j] == badBeast){
                    if(entities[i+moveDirection.X][j+moveDirection.Y] instanceof Wall){

                    }
                    else{
                        entities[i+moveDirection.X][j+moveDirection.Y] = entities[i][j];
                        entities[i][j] = null;
                        return;
                    }

                }
            }
        }
    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

        for(int i = 0; i < entities.length; i++){
            for(int j = 0; j < entities[0].length; j++){
                if(entities[i][j] == masterSquirrel){
                    if(entities[i+moveDirection.X][j+moveDirection.Y] instanceof Wall){
                        collisionCounter=3;
                    }
                    else if(entities[i+moveDirection.X][j+moveDirection.Y] instanceof GoodPlant){
                        masterSquirrel.updateEnergy(entities[i+moveDirection.X][j+moveDirection.Y].getEnergy());
                        killAndReplace(entities[i+moveDirection.X][j+moveDirection.Y]);
                    }
                    else{
                        entities[i+moveDirection.X][j+moveDirection.Y] = entities[i][j];
                        entities[i][j] = null;
                        return;
                    }

                }
            }
        }
    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {
        return null;
    }

    @Override
    public void kill(Entity entity) {
        for(int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[0].length; j++) {
                if(entities[i][j] == entity){
                    entities[i][j] = null;
                    return;
                }
            }
        }
    }

    @Override
    public void killAndReplace(Entity entity) {
        for(int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[0].length; j++) {
                if(entities[i][j] == entity){
                    while(true) {
                        int x = new Random().nextInt(width - 1);
                        int y = new Random().nextInt(height - 1);
                        if (entities[x][y] == null) {
                            entities[x][y] = entity;
                            entities[i][j] = null;
                            return;
                        }
                    }
                }
            }
        }
    }

    @Override
    public EntityType getEntityType(XY xy) {
        if(entities[xy.X][xy.Y] != null)
            return new EntityType(entities[xy.X][xy.Y].getClass());
        else
            return new EntityType(null);
    }

    public Entity[][] getEntities(){
        return entities;
    }

    //Kollision

}
