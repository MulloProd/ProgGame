package Testing;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrelBot;
import org.junit.Test;

import java.util.Random;

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
    public void moveEntity() throws Exception {
        Board board =new Board();
        XY targetPoint = new XY(new Random().nextInt(board.width),new Random().nextInt(board.height));
        Entity entity = null;
        for(int x=0;x<board.width;x++)
        {
            for(int y=0;y<board.height;y++){
                if(board.getEntity(x,y)!=null)
                    entity = board.getEntity(x,y);
            }
        }

        while(board.getEntity(targetPoint.x,targetPoint.y) != null)
            targetPoint = new XY(new Random().nextInt(board.width),new Random().nextInt(board.height));

        board.moveEntity(entity, targetPoint);

        assertEquals(entity.getPosition(), targetPoint);
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
    public void setNewMiniSquirrel() throws Exception {

        Board board = new Board();

        int x = 2;
        int y = 1;
        int energy = 100;

        assertFalse("Out of bounds", x<0||y<0||x>width||y>height);
        assertFalse("Other entity on this place",board.entities[x][y] != null);

        Entity entity = new MiniSquirrelBot(entitySet.getNextFreeID(), energy, new XY(x,y), masterSquirrel);
        entities[x][y] = entity;
        entitySet.addEntity(entity);

        assertTrue(board.setNewMiniSquirrel(x,y,energy,masterSquirrel));

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