package fatsquirrel.core;

import fatsquirrel.State;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.*;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Board {
    private EntitySet entitySet = new EntitySet();
    private final int height;
    private final int width;
    private Entity[][] entities;
    private State state;

    public Board() throws Exception {
        height = BoardConfig.getSize().Y;
        width = BoardConfig.getSize().X;
        entities = new Entity[width][height];
        createRandomBoard();
    }

    public void setState(State state){
        this.state = state;
    }

    public void updateEntitySet() throws IOException {
        entitySet.nextStep(flatten());
    }

    private void createRandomBoard() throws Exception {
        //Wände erstellen
        int counter = BoardConfig.getWallCount();
        //Surrounding Wall, horizontal
        for(int i=0;i<entities.length && counter>0;i++){
            if(setNewEntity(i,0,EntityType.Types.Wall))
                counter--;
            if(counter>0 && setNewEntity(i,entities[i].length-1,EntityType.Types.Wall))
                counter--;
        }
        //Surrounding Wall, vertical
        for(int i=0;i<entities[0].length&&counter>0;i++){
            if(setNewEntity(0,i,EntityType.Types.Wall))
                counter--;
            if(counter>0 && setNewEntity(entities.length-1,i,EntityType.Types.Wall))
                counter--;
        }

        //restliche Wände erstellen
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.Types.Wall))
                counter--;
        }

        //HandOperatedMasterSquirrels erstellen
        counter = BoardConfig.getHandOperatedMasterSquirrelCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.Types.HandOperatedMasterSquirrel))
                counter--;
        }

        /*
        //MiniSquirrels erstellen
        counter = BoardConfig.getMiniSquirrelCount();
        while (counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,new MiniSquirrel(entitySet.getNextFreeID(), 200, new XY(x,y), entitySet.getRandomMasterSquirrel())))
                counter--;
        }
        */

        //Planzen erstellen
        counter = BoardConfig.getPlantCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.Types.GoodPlant))
                counter--;
        }
        counter = BoardConfig.getPlantCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.Types.BadPlant))
                counter--;
        }

        //Beasts erstellen
        counter = BoardConfig.getBeastCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.Types.BadBeast))
                counter--;
        }
        counter = BoardConfig.getBeastCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.Types.GoodBeast))
                counter--;
        }


    }

    public boolean moveEntity(Entity entity, XY newPos){
        if(entities[newPos.X][newPos.Y] == null){
            entities[newPos.X][newPos.Y] = entity;
            entities[entity.getPosition().X][entity.getPosition().Y] = null;
            entity.setPosition(newPos);
            return true;
        }
        else
            return false;
    }

    public void removeEntity(Entity entity){
        entitySet.deleteEntity(entity);
        entities[entity.getPosition().X][entity.getPosition().Y]=null;
    }

    //gibt TRUE zurück wenn setzen erfolgreich war, kann alles mit standartwerten initialisieren (für mini muss was andres verwendet werden
    public boolean setNewEntity(int x, int y, EntityType.Types entityType) {
        if(x<0||y<0||x>width||y>height)
            return false;
        else if(entities[x][y] != null)
            return false;
        else{
            Entity entity = getNewEntityWithType(entityType, new XY(x,y));
            entities[x][y] = entity;
            entitySet.addEntity(entity);
            return true;
        }
    }
    public boolean setNewMiniSquirrel(int x, int y, int energy, MasterSquirrel masterSquirrel){
        if(x<0||y<0||x>width||y>height)
            return false;
        else if(entities[x][y] != null)
            return false;
        else{
            Entity entity = new MiniSquirrel(entitySet.getNextFreeID(), energy, new XY(x,y), masterSquirrel);
            entities[x][y] = entity;
            entitySet.addEntity(entity);
            return true;
        }
    }

    private Entity getNewEntityWithType(EntityType.Types entityType, XY xy){
        switch (entityType){
            case Wall:
                return new Wall(entitySet.getNextFreeID(), -10, xy);
            case BadPlant:
                return new BadPlant(entitySet.getNextFreeID(), -100, xy);
            case GoodPlant:
                return new GoodPlant(entitySet.getNextFreeID(), 100, xy);
            case BadBeast:
                return new BadBeast(entitySet.getNextFreeID(), -150, xy);
            case GoodBeast:
                return new GoodBeast(entitySet.getNextFreeID(), 200, xy);
            case HandOperatedMasterSquirrel:
                return new HandOperatedMasterSquirrel(entitySet.getNextFreeID(), 1000, xy);
            default:
                return null;
        }
    }

    public Entity getEntity(XY xy){
        return getEntity(xy.X,xy.Y);
    }
    public Entity getEntity(int x, int y){
        return entities[x][y];
    }

    public FlattenedBoard flatten(){
        return new FlattenedBoard(width, height, this);
    }

    public String AllEntitiesToString(){
        return entitySet.toString();
    }
}
