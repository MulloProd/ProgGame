package fatsquirrel.botapi.Implementation;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;

public class miniBotController implements BotController {
    @Override
    public void nextStep(ControllerContext view) {

        view.move(XY.randomVector());
    }
}
