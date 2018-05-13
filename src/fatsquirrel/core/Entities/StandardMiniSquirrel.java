package fatsquirrel.core.Entities;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;

public class StandardMiniSquirrel extends MiniSquirrel {
    public StandardMiniSquirrel(int id, int energy, XY position, MasterSquirrel masterSquirrel) {
        super(id, energy, position, masterSquirrel);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        entityContext.tryMove(this, XY.randomVector());
    }
}
