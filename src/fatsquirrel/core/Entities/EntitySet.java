package fatsquirrel.core.Entities;

import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.Logging;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;

import java.io.IOException;
import java.util.*;

public class EntitySet {

    public List<Entity> set = new ArrayList<Entity>();

    private int nextID = 0;
    private Logging logging = new Logging(this.getClass().getName());

    public int getNextFreeID(){
        nextID++;
        return nextID-1;
    }

    public void addEntity(Entity newEntity){
        if(newEntity !=null) {
            logging.getLogger().fine(newEntity.getClass().getSimpleName() + " (ID: " + newEntity.getID() + ") added");
            set.add(newEntity);
        }
        else
            logging.getLogger().warning("newEntity is null!");
    }

    public void deleteEntity(Entity oldEntity){
        logging.getLogger().fine(oldEntity.getClass().getSimpleName() + " (ID: " + oldEntity.getID() + ") removed");
        set.remove(oldEntity);
    }

    public MasterSquirrel getRandomMasterSquirrel(){
        List<Entity> masterSet = new ArrayList<>();

        for(Entity entity : set){
            if (entity instanceof MasterSquirrel){
                masterSet.add(entity);
            }
        }

        int randomMaster = new Random().nextInt(masterSet.size());

        return (MasterSquirrel)masterSet.get(randomMaster);
    }

    public void nextStep(EntityContext entityContext) throws IOException {
        List<Entity> tempList = new ArrayList<>(set);
        for(Entity entity : tempList){
            if(entity!=null && set.contains(entity))
                entity.nextStep(entityContext);
        }
    }

    public ArrayList getBots(){
        ArrayList<Entity> botList = new ArrayList<>();
        for(int i=0; i<set.size(); i++){
            if(set.get(i) instanceof MasterSquirrelBot)
                botList.add(set.get(i));
        }
        return botList;
    }

    public String toString(){
        String allEntities = "";

        for(int i = 0; i<set.size(); i++){
            if (set.get(i) != null) {
                allEntities += set.get(i).toString() + "\n";
            }
        }

        return allEntities;
    }
}
