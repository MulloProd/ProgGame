package fatsquirrel.core;

import fatsquirrel.State;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.*;

import java.io.IOException;
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
            if(setTile(i,0,new Wall(entitySet.getNextFreeID(), -10, new XY(i,0))))
                counter--;
            if(counter>0 && setTile(i,entities[i].length-1,new Wall(entitySet.getNextFreeID(), -10, new XY(i,entities[i].length-1))))
                counter--;
        }
        //Surrounding Wall, vertical
        for(int i=0;i<entities[0].length&&counter>0;i++){
            if(setTile(0,i,new Wall(entitySet.getNextFreeID(), -10, new XY(0,1))))
                counter--;
            if(counter>0 && setTile(entities.length-1,i,new Wall(entitySet.getNextFreeID(), -10, new XY(entities.length-1,1))))
                counter--;
        }

        //restliche Wände erstellen
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setTile(x,y,new Wall(entitySet.getNextFreeID(), -10, new XY(x,y))))
                counter--;
        }

        //Planzen erstellen
        counter = BoardConfig.getPlantCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setTile(x,y,new GoodPlant(entitySet.getNextFreeID(), 100, new XY(x,y))))
                counter--;
        }
        counter = BoardConfig.getPlantCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setTile(x,y,new BadPlant(entitySet.getNextFreeID(), -100, new XY(x,y))))
                counter--;
        }

        //Beasts erstellen
        counter = BoardConfig.getBeastCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setTile(x,y,new BadBeast(entitySet.getNextFreeID(), -150, new XY(x,y))))
                counter--;
        }
        counter = BoardConfig.getBeastCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setTile(x,y,new GoodBeast(entitySet.getNextFreeID(), 200, new XY(x,y))))
                counter--;
        }

        //HandOperatedMasterSquirrels erstellen
        counter = BoardConfig.getMasterSquirrelCount();
        while(counter>0){
            int x = new Random().nextInt(width-1);
            int y = new Random().nextInt(height-1);
            if(setTile(x,y,new HandOperatedMasterSquirrel(entitySet.getNextFreeID(), 1000, new XY(x,y))))
                counter--;
        }
    }

    //gibt TRUE zurück wenn setzen erfolgreich war
    private boolean setTile(int x, int y, Entity entity) throws Exception {
        if(x<0||y<0||x>width||y>height)
            return false;
        else if(entities[x][y] != null)
            return false;
        else{
            entities[x][y] = entity;
            entitySet.addEntity(entity);
            return true;
        }
    }

    public FlattenedBoard flatten(){
        return new FlattenedBoard(entities, width, height, state);
    }
}
