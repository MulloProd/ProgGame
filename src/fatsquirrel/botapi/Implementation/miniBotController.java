package fatsquirrel.botapi.Implementation;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;

import java.util.Random;

public class miniBotController implements BotController {

    private final int impactRadius = 3;         //zwischen >=2 und <=10

    @Override
    public void nextStep(ControllerContext view) {

        if(new Random().nextInt(10) < 2)
            view.doImplosion(impactRadius);
        else
            view.move(XYsupport.randomVector());
    }
}
