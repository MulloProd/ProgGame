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

        //boolean b = view.isMine(new XY(5,4));
        if(new Random().nextInt(10) < 2)
            view.implode(impactRadius);
        else
            view.move(XYsupport.randomVector());
    }
}
