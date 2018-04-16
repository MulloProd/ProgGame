package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class GoodBeast extends Entity {
    public GoodBeast(int id, int energy, XY position) {
        super(id, energy, position);
    }

    @Override
    public void nextStep() {
        setPosition(getPosition().add(XY.randomVector()));
    }
}
