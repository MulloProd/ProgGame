package fatsquirrel.botapi;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    int getEnergy();
    void doImplosion(int impactRadius);
    Direction getMasterDirection();
}
