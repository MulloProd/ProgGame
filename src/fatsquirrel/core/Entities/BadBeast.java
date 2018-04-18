package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class BadBeast extends Entity {
    public BadBeast(int id, int energy, XY position) {
        super(id, energy, position);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        setPosition(getPosition().add(XY.randomVector()));
    }
}
