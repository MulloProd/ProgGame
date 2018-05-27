package fatsquirrel.core.Entities;

import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Logging;

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
        logging.getLogger().info(newEntity.getClass().getSimpleName() + " (ID: " + newEntity.getID() + ") added");
        set.add(newEntity);
    }

    public void deleteEntity(Entity oldEntity){
        logging.getLogger().info(oldEntity.getClass().getSimpleName() + " (ID: " + oldEntity.getID() + ") killed");
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
        //System.out.println(toString());
        int counter = set.size();
        for(int i=0;i<set.size();i++){
            //if(counter > set.size())
              //  i--;
            if(set.get(i)!=null)
                set.get(i).nextStep(entityContext);
        }

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
