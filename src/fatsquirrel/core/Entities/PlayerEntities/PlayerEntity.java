package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.State;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.Entity;

public abstract class PlayerEntity extends Entity {
    //private State state;
    public PlayerEntity(int id, int energy, XY position) {
        super(id, energy, position);
    }
}
