package Testing;

import fatsquirrel.XY;
import fatsquirrel.XYsupport;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntitySet;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.PlayerEntities.*;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BoardTest {

    Mockery context = new Mockery();

    @Test
    public void moveEntity() throws Exception {
        Board board = new Board();

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
    public void setNewMasterSquirrel() {
    }

    @Test
    public void setNewMiniSquirrel() throws Exception {
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
    public void setNewEntity() throws Exception {
        Board board = new Board();
        XY freePos = null;
        XY notFreePos = null;
        for(int x=0;x<board.width;x++)
        {
            for(int y=0;y<board.height;y++){
                if(board.getEntity(x,y)!=null && notFreePos == null) {
                    notFreePos = new XY(x,y);
                }
                else if(board.getEntity(x,y)== null && freePos == null)
                    freePos = new XY(x,y);
            }
        }
        assertTrue(board.setNewEntity(freePos.x,freePos.y, EntityType.GOOD_PLANT));
        assertFalse(board.setNewEntity(notFreePos.x,notFreePos.y, EntityType.GOOD_PLANT));
        assertFalse(board.setNewEntity(notFreePos.x,notFreePos.y, EntityType.NONE));
        assertFalse(board.setNewEntity(notFreePos.x,notFreePos.y, EntityType.MINI_SQUIRREL));
        assertFalse(board.setNewEntity(notFreePos.x,notFreePos.y, EntityType.MASTER_SQUIRREL));

        board.removeEntity(board.getEntity(freePos));
        assertTrue(board.setNewMasterSquirrel(freePos.x,freePos.y, HandOperatedMasterSquirrel.class));
        board.removeEntity(board.getEntity(freePos));
        assertFalse(board.setNewMasterSquirrel(notFreePos.x,notFreePos.y, HandOperatedMasterSquirrel.class));
        assertFalse(board.setNewMasterSquirrel(freePos.x,freePos.y, MiniSquirrel.class));

        assertTrue(board.setNewMiniSquirrel(freePos.x,freePos.y, 50, new HandOperatedMasterSquirrel(0,100,XY.ZERO_ZERO)));
        board.removeEntity(board.getEntity(freePos));
        assertFalse(board.setNewMiniSquirrel(notFreePos.x,notFreePos.y, 50, new HandOperatedMasterSquirrel(0,100,XY.ZERO_ZERO)));
        assertFalse(board.setNewMiniSquirrel(freePos.x,freePos.y, 50, null));

    }
}