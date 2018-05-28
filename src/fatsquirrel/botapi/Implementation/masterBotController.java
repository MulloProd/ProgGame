package fatsquirrel.botapi.Implementation;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;

import java.util.Random;

public class masterBotController implements BotController{
    @Override
    public void nextStep(ControllerContext view) {
    XY lowerLeft = view.getViewLowerLeft();
        XY upperRight = view.getViewUpperRight();

        view.move(XY.randomVector());

        if(new Random().nextInt(10) < 2)
            view.spawnMiniBot(XY.randomVector(), 100);
    }
}
