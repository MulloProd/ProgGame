package fatsquirrel.botapi.Implementation;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;

import java.util.Random;

public class masterBotController implements BotController{
    @Override
    public void nextStep(ControllerContext view) {
    XY lowerLeft = view.getViewLowerLeft();
        XY upperRight = view.getViewUpperRight();

        view.move(XYsupport.randomVector());

        if(new Random().nextInt(10) < 2)
            view.spawnMiniBot(XYsupport.randomVector(), 100);
    }
}
