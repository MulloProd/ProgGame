package Testing;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrelBot;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private final int width = 15;
    private final int height = 15;
    private final MasterSquirrel masterSquirrel = new MasterSquirrelBot(0,1000,new XY(1,1));

    private final EntitySet entitySet = new EntitySet();
    private Entity[][] entities;

    @Test
    public void updateEntitySet() {
    }

    @Test
    public void moveEntity() {
    }

    @Test
    public void removeEntity() {
    }

    @Test
    public void setNewEntity() {
    }

    @Test
    public void setNewMasterSquirrel() {
    }

    @Test
    public void setNewMiniSquirrel() {

        int x = 2;
        int y = 1;
        int energy = 100;

        assertFalse("Out of bounds", x<0||y<0||x>width||y>height);
        assertFalse("Other entity on this place",entities[x][y] != null);

        Entity entity = new MiniSquirrelBot(entitySet.getNextFreeID(), energy, new XY(x,y), masterSquirrel);
        entities[x][y] = entity;
        entitySet.addEntity(entity);

    }

    @Test
    public void getEntity() {
    }

    @Test
    public void getEntity1() {
    }

    @Test
    public void flatten() {
    }

    @Test
    public void allEntitiesToString() {
    }
}