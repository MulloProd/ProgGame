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

        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;

        int x = miniSquirrel.getPosition().X+moveDirection.X;
        int y = miniSquirrel.getPosition().Y+moveDirection.Y;

        //Noch Energy vorhanden
        if (miniSquirrel.getEnergy() >= 1) {
            if(entities[x][y] == null) {
                moveEntityToNullSpace(miniSquirrel, moveDirection);
                miniSquirrel.updateEnergy(-1);
            }
            else if(entities[x][y] instanceof MasterSquirrel){
                //Kollision mit eigenem MasterSquirrel
                if(entities[x][y] == miniSquirrel.getMasterSquirrel()){
                    miniSquirrel.updateEnergy(-1);
                    entities[x][y].updateEnergy(miniSquirrel.getEnergy());
                    kill(miniSquirrel);
                }
                else {
                    kill(miniSquirrel);
                }
            }
            else if(entities[x][y] instanceof MiniSquirrel){
                MiniSquirrel miniSquirrelTemp = (MiniSquirrel)entities[x][y];
                //Kollision mit fremden MiniSquirrel
                if(miniSquirrel.getMasterSquirrel() != miniSquirrelTemp.getMasterSquirrel()){
                    miniSquirrel.updateEnergy(0);
                    entities[x][y].updateEnergy(0);
                    kill(miniSquirrel);
                    kill(entities[x][y]);
                }
            }
        }
        else if (miniSquirrel.getEnergy() == 0) {
            kill(miniSquirrel);
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;

        int xGoodBeast = goodBeast.getPosition().X;
        int yGoodBeast = goodBeast.getPosition().Y;

        if(entities[xGoodBeast+moveDirection.X][yGoodBeast+moveDirection.Y] == null){
            moveEntityToNullSpace(goodBeast,moveDirection);
        }
        else if(entities[xGoodBeast+moveDirection.X][yGoodBeast+moveDirection.Y] instanceof PlayerEntity){
            entities[xGoodBeast+moveDirection.X][yGoodBeast+moveDirection.Y].updateEnergy(goodBeast.getEnergy());
            killAndReplace(goodBeast);
        }

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {
        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;

        int xBadBeast = badBeast.getPosition().X;
        int yBadBeast = badBeast.getPosition().Y;

        if(entities[xBadBeast+moveDirection.X][yBadBeast+moveDirection.Y] == null){
            moveEntityToNullSpace(badBeast,moveDirection);
        }
        else if(entities[xBadBeast+moveDirection.X][yBadBeast+moveDirection.Y] instanceof PlayerEntity){
            if(badBeast.getBiteCounter()>1){
                entities[xBadBeast+moveDirection.X][yBadBeast+moveDirection.Y].updateEnergy(badBeast.getEnergy());
                badBeast.setBiteCounter(-1);
            }
            else if(badBeast.getBiteCounter()==1){
                entities[xBadBeast+moveDirection.X][yBadBeast+moveDirection.Y].updateEnergy(badBeast.getEnergy());
                badBeast.setBiteCounter(7); //Reset BiteCounter
                killAndReplace(badBeast);
            }
        }
    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

        if(moveDirection.X == 0 && moveDirection.Y == 0)
            return;

        //aktuelle Position holen
        int xMasterSquirrel = masterSquirrel.getPosition().X;
        int yMasterSquirrel = masterSquirrel.getPosition().Y;

        //Kollisionsabfrage
        if(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] == null){
            moveEntityToNullSpace(masterSquirrel,moveDirection);
        }
        else if(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] instanceof Wall){
            masterSquirrel.setCollisionCounter(3);
            masterSquirrel.updateEnergy(-50);
            return;
        }
        else if(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] instanceof GoodPlant
                || entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] instanceof BadPlant){
            masterSquirrel.updateEnergy(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y].getEnergy());
            killAndReplace(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y]);

            moveEntityToNullSpace(masterSquirrel,moveDirection);
        }
        else if(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] instanceof GoodBeast
                || entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] instanceof BadBeast){
            masterSquirrel.updateEnergy(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y].getEnergy());
            killAndReplace(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y]);

            moveEntityToNullSpace(masterSquirrel,moveDirection);
        }
        else if(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] instanceof MiniSquirrel){
            MasterSquirrel masterSquirrelTemp = ((MiniSquirrel)entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y]).getMasterSquirrel();
            if(masterSquirrelTemp == masterSquirrel){
                masterSquirrel.updateEnergy(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y] .getEnergy());
                kill(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y]);
            }
            else{
                masterSquirrel.updateEnergy(150);
                kill(entities[xMasterSquirrel+moveDirection.X][yMasterSquirrel+moveDirection.Y]);
            }

            moveEntityToNullSpace(masterSquirrel,moveDirection);
        }

        if(masterSquirrel.getEnergy() <=0){
            System.out.println("Verloren!");
            System.exit(0);
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
        entities[entity.getPosition().X][entity.getPosition().Y] = null;
        //Aus Liste noch löschen?

    }

    @Override
    public void killAndReplace(Entity entity) {

        while(true) {
            int x = new Random().nextInt(width - 1);
            int y = new Random().nextInt(height - 1);
            if (entities[x][y] == null) {
                entities[x][y] = entity;
                entities[entity.getPosition().X][entity.getPosition().Y] = null;
                entity.setPosition(new XY(x,y));
                return;
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

    public State getState() {
        return state;
    }

    public void moveEntityToNullSpace(Entity entity, XY moveDirection){

        //Entity bewegt sich auf ein leeres Feld
        int xEntity = entity.getPosition().X;
        int yEntity = entity.getPosition().Y;

        entity.setPosition(new XY(entity.getPosition().X + moveDirection.X, entity.getPosition().Y + moveDirection.Y));
        entities[xEntity + moveDirection.X][yEntity + moveDirection.Y] = entities[xEntity][yEntity];
        entities[xEntity][yEntity] = null;
        return;

    }
}
