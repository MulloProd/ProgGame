package fatsquirrel.core.Entities;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.PlayerEntity;

public interface EntityContext {

    XY getSize();
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
    PlayerEntity nearestPlayerEntity(XY pos);

    void kill(Entity entity);
    void killAndReplace(Entity entity);
    EntityType getEntityType(XY xy);

    void spawn_Mini(XY xy, int energy, MasterSquirrel masterSquirrel);

    Entity getEntityAt(int x, int y);
}
