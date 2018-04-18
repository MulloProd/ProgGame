package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class MiniSquirrel extends PlayerEntity {

    private MasterSquirrel masterSquirrel;

    public MiniSquirrel(int id, int energy, XY position, MasterSquirrel masterSquirrel) {
        super(id, energy, position);

        masterSquirrel.updateEnergy(-energy);

        this.masterSquirrel = masterSquirrel;
    }

    @Override
    public void nextStep(EntityContext entityContext) {

    }
}
