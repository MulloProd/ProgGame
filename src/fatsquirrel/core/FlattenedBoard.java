package fatsquirrel.core;

import fatsquirrel.State;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.*;

import java.util.Random;

public class FlattenedBoard implements EntityContext, BoardView {

    private final Entity[][] entities;
    private final int width;
    private final int height;
    private State state;

    public FlattenedBoard(Entity[][] entities, int width, int height, State state){
        this.entities = entities;
        this.width = width;
        this.height = height;
        this.state = state;
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

        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;

        for(int i = 0; i < entities.length; i++){
            for(int j = 0; j < entities[0].length; j++){
                if(entities[i][j] == goodBeast){
                    if(entities[i+moveDirection.X][j+moveDirection.Y] == null){
                        entities[i+moveDirection.X][j+moveDirection.Y] = entities[i][j];
                        entities[i][j] = null;
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;
        for(int i = 0; i < entities.length; i++){
            for(int j = 0; j < entities[0].length; j++){
                if(entities[i][j] == badBeast){
                    if(entities[i+moveDirection.X][j+moveDirection.Y] == null){
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

        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;

        for(int i = 0; i < entities.length; i++){
            for(int j = 0; j < entities[0].length; j++){
                if(entities[i][j] == masterSquirrel){
                    if(entities[i+moveDirection.X][j+moveDirection.Y] == null){
                        entities[i+moveDirection.X][j+moveDirection.Y] = entities[i][j];
                        entities[i][j] = null;
                        return;
                    }
                    else if(entities[i+moveDirection.X][j+moveDirection.Y] instanceof Wall){
                        masterSquirrel.collisionCounter=3;
                        masterSquirrel.updateEnergy(-50);
                    }
                    else if(entities[i+moveDirection.X][j+moveDirection.Y] instanceof GoodPlant || entities[i+moveDirection.X][j+moveDirection.Y] instanceof BadPlant){
                        masterSquirrel.updateEnergy(entities[i+moveDirection.X][j+moveDirection.Y].getEnergy());
                        killAndReplace(entities[i+moveDirection.X][j+moveDirection.Y]);
                    }
                }
            }
        }
    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {

        PlayerEntity nearestPlayerEntity = null;
        int xDiff = width;
        int yDiff = height;

        for(int i = 0; i < entities.length; i++) {
            for (int j = 0; j < entities[0].length; j++) {
                if(entities[i][j] instanceof PlayerEntity){
                    if(Math.abs(pos.X-i)+Math.abs(pos.Y-j)<xDiff+yDiff){
                        xDiff = Math.abs(pos.X-i);
                        yDiff = Math.abs(pos.Y-j);
                        nearestPlayerEntity = (PlayerEntity)entities[i][j];
                    }
                }
            }
        }

        return nearestPlayerEntity;
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


    public void tryMove(Entity entity){

    }

    public State getState() {
        return state;
    }
}
