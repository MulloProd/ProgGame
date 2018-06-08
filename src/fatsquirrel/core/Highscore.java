package fatsquirrel.core;

import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntitySet;
import org.jmock.Mockery;

import java.util.*;
import java.util.Map;
import java.util.HashMap;

public class Highscore {

    private static Map<String, Map<Integer, Integer>> score = new HashMap();

    public Highscore(){
        for(String botName : BoardConfig.getBotNames())
            score.put(botName, new HashMap());
    }

    public static void calculateHighscore(ArrayList<Entity> entityList, int round, String importedHighscore) {

        //Convert String to Highscore
        //...


        //Key: Botname, Value: (Key:Runde, Value: Highscore)

        String[] botNames = BoardConfig.getBotNames();
        Map<Integer,Integer> mapTemp = new HashMap<>();

        for(int i = 0; i<entityList.size(); i++) {
            mapTemp.put(round,entityList.get(i).getEnergy());
            score.put(botNames[i], mapTemp);
            mapTemp = new HashMap<>();
        }
        System.out.println(score);


    }

    public String toString(){
        return score.toString();
    }
}
