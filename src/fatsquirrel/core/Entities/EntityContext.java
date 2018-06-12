package fatsquirrel.core.Entities;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.PlayerEntity;

public interface EntityContext {

    /**
     *
     * @return the size of the size of the board
     */
    XY getSize();
    void tryMove(MiniSquirrel miniSquirrel, XY moveDirection);
    void tryMove(GoodBeast goodBeast, XY moveDirection);
    void tryMove(BadBeast badBeast, XY moveDirection);
    void tryMove(MasterSquirrel masterSquirrel, XY moveDirection);
    /**
     *@param pos : position of current entity
     * @return the newest master oder minisquirrel
     */
    PlayerEntity nearestPlayerEntity(XY pos);

    /**
     * kills given entity
     *@param entity : entity wich should be killed
     */
    void kill(Entity entity);
    /**
     *  kills given entity and instantiate new one at random position
     *@param entity : entity wich should be killed
     */
    void killAndReplace(Entity entity);
    /**
     *@param xy : Position on board
     * @return type of entity at given position
     */
    EntityType getEntityType(XY xy);

    /**
     *
     * @param xy : direction where the mini should be spawned based on master XY
     * @param energy : the used energy
     * @param masterSquirrel : master of the mini
     */
    void spawn_Mini(XY xy, int energy, MasterSquirrel masterSquirrel);

    /**
     *@param x : xPos
     * @param y : yPos
     * @return entity at given position
     */
    Entity getEntityAt(int x, int y);
}
