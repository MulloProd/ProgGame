package fatsquirrel.botimpls;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;
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
                view.spawnMiniBot(XYsupport.randomVector(), energyNewMini);
            } catch (SpawnException e) {
                e.printStackTrace();
            }
        }
    }
}
