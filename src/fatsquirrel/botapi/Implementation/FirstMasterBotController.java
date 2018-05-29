package fatsquirrel.botapi.Implementation;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.OutOfViewException;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.GoodPlant;

import java.util.ArrayList;
import java.util.List;

public class FirstMasterBotController implements BotController {
    ControllerContext view;
    @Override
    public void nextStep(ControllerContext view) {
        this.view = view;
        List<XY> targetPosList = findEntityType(EntityType.GOOD_BEAST);
        targetPosList.addAll(findEntityType(EntityType.GOOD_PLANT));
        if(targetPosList.size() >0){
            XY targetPos = null;
            for(XY xy : targetPosList){
                if(targetPos == null)
                    targetPos = xy;
                else if(xy.distanceFrom(view.locate()) < targetPos.distanceFrom(view.locate()))
                    targetPos = xy;
            }
            if(targetPos != null){
                int x = (targetPos.x<view.locate().x)?-1:1;
                int y = (targetPos.y<view.locate().y)?-1:1;
                XY moveDir = new XY(x,y);
                move(moveDir);
            }
        }
        moveRandom();
    }

    private List<XY> findEntityType(EntityType entityType) {
        List<XY> list = new ArrayList<>();
        for (int x = 0; x < Math.abs(view.getViewUpperRight().x - view.getViewLowerLeft().x); x++) {
            for (int y = 0; y < Math.abs(view.getViewUpperRight().y - view.getViewLowerLeft().y); y++) {
                try {
                    if (view.getEntityAt(view.getViewLowerLeft().plus(new XY(x, -y))) == entityType) {
                        list.add(new XY(x, -y));
                    }
                } catch (OutOfViewException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    private void move(XY dir){
        if(checkPath(view.locate().plus(dir)))
            view.move(dir);
        else{
            int x = dir.x;
            int y = dir.y;
            if(x!=1)
                x++;
            else if(y!=1)
                y++;
            else
                x--;
            move(new XY(x,y));
        }
    }

    private void moveRandom(){
        boolean success = false;
        while(!success){
            XY dir = XYsupport.randomVector();
            try {
                if(view.getEntityAt(view.locate().plus(dir)) == EntityType.NONE){
                    view.move(dir);
                    success = true;
                }
            } catch (OutOfViewException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean checkPath(XY targetPos){
        try {
            if(view.getEntityAt(targetPos) == EntityType.NONE ||
                    view.getEntityAt(targetPos) == EntityType.GOOD_BEAST
                    ||view.getEntityAt(targetPos) == EntityType.GOOD_PLANT)
                return true;
        } catch (OutOfViewException e) {
            e.printStackTrace();
        }

        return false;
    }
}
