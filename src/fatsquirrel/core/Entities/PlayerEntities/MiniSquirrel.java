package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.core.Entities.EntityContext;

public class MiniSquirrel extends PlayerEntity {

    private MasterSquirrel masterSquirrel;
    private int energy;

    public MiniSquirrel(int id, int energy, XY position, MasterSquirrel masterSquirrel) {
        super(id, energy, position);

        masterSquirrel.updateEnergy(-energy);

        this.masterSquirrel = masterSquirrel;
        this.energy = energy;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        entityContext.tryMove(this, XYsupport.randomVector());
    }

    public MasterSquirrel getMasterSquirrel() {
        return masterSquirrel;
    }
}
