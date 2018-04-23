package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class BadBeast extends Entity {
    public BadBeast(int id, int energy, XY position) {
        super(id, energy, position);
        nextStepCounter = 4;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        //setPosition(getPosition().add(XY.randomVector()));

        //nextStep nur bei jedem 4. Schritt
        if(nextStepCounter == 4){
            entityContext.tryMove(this, XY.randomVector());
            nextStepCounter--;
        }
        else if(nextStepCounter == 1){
            nextStepCounter = 4;
        }
        else {
            nextStepCounter--;
        }
    }
}
