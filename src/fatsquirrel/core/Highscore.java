package fatsquirrel.core;

import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntitySet;
import org.jmock.Mockery;

import java.util.*;
import java.util.Map;
import java.util.HashMap;

public class Highscore {

    public static void showHighscore(EntitySet entitySet) {

        //Key: Botname, Value: Key:Runde, Value: Highscore
        Map<String, Map<Integer, Integer>> scores = new HashMap();
        ArrayList<Entity> botList = entitySet.getBots();

        for(int i = 0; i < BoardConfig.getBotNames().length; i++){
            for(int j=0; j<botList.size(); j++){
                if(botList.get(j).toString().contains(BoardConfig.getBotNames()[i])){
                    //Fehlt noch: bisher erreichte Highscores einer Entität in eine Liste packen und dem Value
                    //in der Hashmap hinzufügen
                    //scores.put(BoardConfig.getBotNames()[i], botList);
                }
            }
        }
    }
}
