package fatsquirrel.botimpls;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.OutOfViewException;
import fatsquirrel.botapi.SpawnException;

import java.util.Random;

public class randomMasterBotController implements BotController{
    @Override
    public void nextStep(ControllerContext view) {
        XY lowerLeft = view.getViewLowerLeft();
        XY upperRight = view.getViewUpperRight();

        view.move(XYsupport.randomVector());

        int energyNewMini = 100;

        if(new Random().nextInt(10) < 2){
            try {
                XY dir = XYsupport.randomVector();
                int counter = 3;
                while(view.getEntityAt(view.locate().plus(dir)) != null && counter>0){
                    counter--;
                    dir = XYsupport.randomVector();
                }
                if(view.getEntityAt(view.locate().plus(dir)) == null)
                    view.spawnMiniBot(dir, energyNewMini);
            } catch (SpawnException e) {
                e.printStackTrace();
            } catch (OutOfViewException e) {
                e.printStackTrace();
            }
        }
    }
}
