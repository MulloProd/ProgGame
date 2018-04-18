package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public abstract class PlayerEntity extends Entity {
    public PlayerEntity(int id, int energy, XY position) {
        super(id, energy, position);
    }
}
