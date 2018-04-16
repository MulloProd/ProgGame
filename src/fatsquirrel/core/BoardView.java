package fatsquirrel.core;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntityType;

public interface BoardView {
    EntityType getEntityType(int x, int y);
    XY getSize();
}
