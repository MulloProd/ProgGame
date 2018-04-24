package fatsquirrel.core.Entities;

import javafx.collections.transformation.SortedList;

import java.io.IOException;
import java.util.*;

public class EntitySet {

    public List<Entity> set = new ArrayList<Entity>();

    public int getNextFreeID(){
        int runner = 0;
        for(int i=0;i<set.size();i++){
            if(set.get(i).getID()>runner)
                return runner;
            else
                runner++;
        }
        return runner;
    }

    public void addEntity(Entity newEntity){
        set.add(newEntity);
        Collections.sort(set, (e1, e2) -> (e1.getID() > e2.getID()) ? 1 : -1);
    }

    public void deleteEntity(Entity oldEntity){
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
        System.out.println(toString());
        for(Entity e : set){
            if(e!=null)
                e.nextStep(entityContext);
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
