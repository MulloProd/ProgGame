package fatsquirrel.core;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityType;

public interface BoardView {
    /**
     *@param x : xPos on board
     * @param y : yPos on board
     * @return type of entity at given position
     */
    EntityType getEntityType(int x, int y);
    /**
     *
     * @return the size of the size of the board
     */
    XY getSize();
    /**
     *@param x : xPos
     * @param y : yPos
     * @return entity at given position
     */
    Entity getEntityAt(int x, int y);
}
