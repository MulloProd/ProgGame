package fatsquirrel.core.Entities;

import fatsquirrel.State;
import fatsquirrel.XY;

public abstract class PlayerEntity extends Entity {
    //private State state;
    public PlayerEntity(int id, int energy, XY position) {
        super(id, energy, position);
    }
}
