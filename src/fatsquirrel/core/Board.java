package fatsquirrel.core;

import fatsquirrel.Logging;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Entities.PlayerEntities.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.logging.Level;

public class Board {
    private EntitySet entitySet = new EntitySet();
    public final int height;
    public final int width;
    private Entity[][] entities;
    private Logging logging = new Logging(this.getClass().getName());

    public Board() {
        height = BoardConfig.getSize().y;
        width = BoardConfig.getSize().x;
        entities = new Entity[width][height];
        createRandomBoard();
    }
    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        entities = new Entity[width][height];
    }
    public void updateEntitySet() throws IOException {
        entitySet.nextStep(flatten());
    }

    private void createRandomBoard() {
        //Wände erstellen
        int counter = BoardConfig.getWallCount();
        //Surrounding Wall, horizontal
        for(int i=0;i<entities.length && counter>0;i++){
            if(setNewEntity(i,0,EntityType.WALL))
                counter--;
            if(counter>0 && setNewEntity(i,entities[i].length-1,EntityType.WALL))
                counter--;
        }
        //Surrounding Wall, vertical
        for(int i=0;i<entities[0].length&&counter>0;i++){
            if(setNewEntity(0,i,EntityType.WALL))
                counter--;
            if(counter>0 && setNewEntity(entities.length-1,i,EntityType.WALL))
                counter--;
        }

        //restliche Wände erstellen
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);

            int fixedWalls = new Random().nextInt(5);
            int randVertHorz = new Random().nextInt(2);

            if(randVertHorz==0) {
                if (x + fixedWalls <= width - 1) {
                    for (int i = x + fixedWalls; i > x; i--) {
                        if (setNewEntity(i, y, EntityType.WALL))
                            counter--;
                    }
                }
            }
            else{
                if (y + fixedWalls <= height - 1) {
                    for (int i = y + fixedWalls; i > y; i--) {
                        if (setNewEntity(x, i, EntityType.WALL))
                            counter--;
                    }
                }
            }
        }

        //HandOperatedMasterSquirrels erstellen
        counter = BoardConfig.getHandOperatedMasterSquirrelCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewMasterSquirrel(x,y,HandOperatedMasterSquirrel.class))
                counter--;
        }

        //MasterSquirrelBot erstellen
        counter = BoardConfig.getbotMasterSquirrelCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewMasterSquirrel(x,y,MasterSquirrelBot.class))
                counter--;
        }

        //Planzen erstellen
        counter = BoardConfig.getPlantCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.GOOD_PLANT))
                counter--;
        }
        counter = BoardConfig.getPlantCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.BAD_PLANT))
                counter--;
        }

        //Beasts erstellen
        counter = BoardConfig.getBeastCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.BAD_BEAST))
                counter--;
        }
        counter = BoardConfig.getBeastCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setNewEntity(x,y,EntityType.GOOD_BEAST))
                counter--;
        }

        logging.getLogger().info("Random board created");
    }

    public boolean moveEntity(Entity entity, XY newPos){
        if(entities[newPos.x][newPos.y] == null && entity != null){
            entities[newPos.x][newPos.y] = entity;
            entities[entity.getPosition().x][entity.getPosition().y] = null;
            entity.setPosition(newPos);
            logging.getLogger().finer(entity.getClass().getSimpleName() + "#" + entity.getID() + ": Entity moved to ("+newPos.x +"/"+newPos.y +")");
            return true;
        }
        else
            return false;
    }

    public void removeEntity(Entity entity){
        entitySet.deleteEntity(entity);
        entities[entity.getPosition().x][entity.getPosition().y]=null;
        logging.getLogger().finer(entity.getClass().getSimpleName()+ "#"+entity.getID()+" removed!");
    }

    //gibt TRUE zurück wenn setzen erfolgreich war, kann alles mit standartwerten initialisieren (für mini muss was andres verwendet werden
    public boolean setNewEntity(int x, int y, EntityType entityType) {
        if(x<0||y<0||x>width||y>height)
            return false;
        else if(entities[x][y] != null)
            return false;
        else{
            Entity entity = getNewEntityWithType(entityType, new XY(x,y));
            if(entity != null) {
                entities[x][y] = entity;
                entitySet.addEntity(entity);
                logging.getLogger().finer("new " + entity.getClass().getSimpleName() + " added! " + "#" + entity.getID());
                return true;
            }
            else {
                logging.getLogger().severe("New Entity is null! " + entityType.toString());
                return false;
            }
        }
    }

    public boolean setNewMasterSquirrel(int x, int y, Class masterSquirrel) {
        if (x < 0 || y < 0 || x > width || y > height)
            return false;
        else if (entities[x][y] != null)
            return false;
        else {
            Class[] cArg = new Class[3];
            cArg[0] = int.class;
            cArg[1] = int.class;
            cArg[2] = XY.class;

            int id = entitySet.getNextFreeID();
            int energy = 1000;
            XY pos = new XY(x,y);

            Entity entity = null;
            try {
                entity = (Entity)masterSquirrel.getDeclaredConstructor(cArg).newInstance(id, energy, pos);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                return false;

            }
            entities[x][y] = entity;
            entitySet.addEntity(entity);
            logging.getLogger().finer("new " + entity.getClass().getSimpleName() + " added! " + "#" + entity.getID());
            return true;
        }
    }

    public boolean setNewMiniSquirrel(int x, int y, int energy, MasterSquirrel masterSquirrel){
        if(x<0||y<0||x>width||y>height)
            return false;
        else if(entities[x][y] != null ||masterSquirrel == null)
            return false;
        else{
            Entity entity = new MiniSquirrelBot(entitySet.getNextFreeID(), energy, new XY(x,y), masterSquirrel);
            entities[x][y] = entity;
            entitySet.addEntity(entity);
            logging.getLogger().finer("new "+entity.getClass().getSimpleName()+" added! "+"#"+entity.getID());
            return true;
        }
    }

    private Entity getNewEntityWithType(EntityType entityType, XY xy){
        switch (entityType){
            case WALL:
                return new Wall(entitySet.getNextFreeID(), -10, xy);
            case BAD_PLANT:
                return new BadPlant(entitySet.getNextFreeID(), -100, xy);
            case GOOD_PLANT:
                return new GoodPlant(entitySet.getNextFreeID(), 100, xy);
            case BAD_BEAST:
                return new BadBeast(entitySet.getNextFreeID(), -150, xy);
            case GOOD_BEAST:
                return new GoodBeast(entitySet.getNextFreeID(), 200, xy);/*
            case HandOperatedMasterSquirrel:
                return new HandOperatedMasterSquirrel(entitySet.getNextFreeID(), 1000, xy);
            case MasterSquirrelBot:
                return new MasterSquirrelBot(entitySet.getNextFreeID(), 1000, xy);*/
            default:
                return null;
        }
    }

    public Entity getEntity(XY xy){
        return getEntity(xy.x,xy.y);
    }

    public Entity getEntity(int x, int y){
        return entities[x][y];
    }

    public FlattenedBoard flatten(){
        return new FlattenedBoard(width, height, this);
    }

    public String allEntitiesToString(){
        return entitySet.toString();
    }
}
