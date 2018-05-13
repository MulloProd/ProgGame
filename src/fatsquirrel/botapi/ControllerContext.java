package fatsquirrel.botapi;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntityType;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    int getEnergy();
}
