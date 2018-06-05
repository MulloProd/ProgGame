package Testing;

import fatsquirrel.XY;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.GoodBeast;
import fatsquirrel.core.Entities.BadBeast;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.PlayerEntities.*;
import fatsquirrel.core.FlattenedBoard;
import org.jmock.Mockery;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.*;

public class FlattenedBoardTest {

    Mockery context = new Mockery();

    @Test
    public void BadBeastPlant_Wall_Beast(){
        Board board = new Board(2,2);
        FlattenedBoard flattenedBoard = new FlattenedBoard(2,2, board);
        board.setNewEntity(0,0,EntityType.BAD_BEAST);
        Entity badBeast = board.getEntity(0,0);

        board.setNewEntity(1,1, EntityType.GOOD_BEAST);
        Entity other = board.getEntity(1,1);
        BadBeastPlant_Wall_Beast_Helper(badBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.BAD_BEAST);
        other = board.getEntity(1,1);
        BadBeastPlant_Wall_Beast_Helper(badBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.GOOD_PLANT);
        other = board.getEntity(1,1);
        BadBeastPlant_Wall_Beast_Helper(badBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.BAD_PLANT);
        other = board.getEntity(1,1);
        BadBeastPlant_Wall_Beast_Helper(badBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.WALL);
        other = board.getEntity(1,1);
        BadBeastPlant_Wall_Beast_Helper(badBeast,other,flattenedBoard,board);
    }
    private void BadBeastPlant_Wall_Beast_Helper(Entity badBeast, Entity other, FlattenedBoard flattenedBoard, Board board){
        int oldEnergy= other.getEnergy();
        int oldBadBeastCounter = ((BadBeast)badBeast).getBiteCounter();
        flattenedBoard.tryMove((BadBeast)badBeast,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), badBeast);
        assertEquals(oldEnergy, other.getEnergy());
        assertEquals(oldBadBeastCounter, ((BadBeast) badBeast).getBiteCounter());
        board.removeEntity(other);

    }
    @Test
    public void BadBeastMaster() {
        Board board = new Board(2,2);
        FlattenedBoard flattenedBoard = new FlattenedBoard(2,2, board);
        board.setNewEntity(0,0,EntityType.BAD_BEAST);
        Entity badBeast = board.getEntity(0,0);

        board.setNewMasterSquirrel(1,1, HandOperatedMasterSquirrel.class);
        Entity other = board.getEntity(1,1);
        int oldEnergy= other.getEnergy();
        int oldBadBeastCounter = ((BadBeast)badBeast).getBiteCounter();
        flattenedBoard.tryMove((BadBeast)badBeast,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), badBeast);
        assertNotEquals(oldEnergy, other.getEnergy());
        assertEquals (oldBadBeastCounter-1, ((BadBeast) badBeast).getBiteCounter());
    }
    @Test
    public void BadBeastMini() {
        Board board = new Board(2,2);
        FlattenedBoard flattenedBoard = new FlattenedBoard(2,2, board);
        board.setNewEntity(0,0,EntityType.BAD_BEAST);
        Entity badBeast = board.getEntity(0,0);

        board.setNewMiniSquirrel(1,1, 50,new HandOperatedMasterSquirrel(0,1000,new XY(2,2)));
        Entity other = board.getEntity(1,1);
        int oldEnergy= other.getEnergy();
        int oldBadBeastCounter = ((BadBeast)badBeast).getBiteCounter();
        flattenedBoard.tryMove((BadBeast)badBeast,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), badBeast);
        assertNotEquals(oldEnergy, other.getEnergy());
        assertEquals (oldBadBeastCounter-1, ((BadBeast) badBeast).getBiteCounter());
    }

    @Test
    public void MasterPlant_Plant_GoodBeast(){
        Board board = new Board(5,5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5,5, board);
        board.setNewMasterSquirrel(0,0, HandOperatedMasterSquirrel.class);
        Entity master = board.getEntity(0,0);

        board.setNewEntity(1,1, EntityType.GOOD_PLANT);
        Entity other = board.getEntity(1,1);
        int oldEnergy= master.getEnergy();
        int otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MasterSquirrel) master,new XY(1,1));
        assertEquals(board.getEntity(1,1), master);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, master.getEnergy());

        board.moveEntity(master, new XY(0,0));
        board.setNewEntity(1,1, EntityType.BAD_PLANT);
        other = board.getEntity(1,1);
        oldEnergy= master.getEnergy();
        otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MasterSquirrel) master,new XY(1,1));
        assertEquals(board.getEntity(1,1), master);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, master.getEnergy());

        board.moveEntity(master, new XY(0,0));
        board.setNewEntity(1,1, EntityType.GOOD_BEAST);
        other = board.getEntity(1,1);
        oldEnergy= master.getEnergy();
        otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MasterSquirrel) master,new XY(1,1));
        assertEquals(board.getEntity(1,1), master);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, master.getEnergy());

    }

    @Test
    public void MasterWall() {
        Board board = new Board(5, 5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5, 5, board);
        board.setNewMasterSquirrel(0, 0, HandOperatedMasterSquirrel.class);
        Entity master = board.getEntity(0, 0);

        board.setNewEntity(1, 1, EntityType.WALL);
        Entity other = board.getEntity(1, 1);
        int oldEnergy = master.getEnergy();
        int otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MasterSquirrel) master, new XY(1, 1));
        assertEquals(board.getEntity(1, 1), other);
        assertEquals(board.getEntity(0, 0), master);
        assertEquals(oldEnergy + otherEnergy, master.getEnergy());
        assertEquals(master.getCollisionCounter(),3);
    }

    @Test
    public void Master_BadBeast(){
        Board board = new Board(5, 5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5, 5, board);
        board.setNewMasterSquirrel(0, 0, HandOperatedMasterSquirrel.class);
        Entity master = board.getEntity(0, 0);

        board.setNewEntity(1, 1, EntityType.BAD_BEAST);
        Entity other = board.getEntity(1, 1);
        int oldEnergy = master.getEnergy();
        int otherEnergy = other.getEnergy();
        int beastBiteCounter = ((BadBeast)other).getBiteCounter();
        flattenedBoard.tryMove((MasterSquirrel) master, new XY(1, 1));
        assertEquals(board.getEntity(1, 1), other);
        assertEquals(board.getEntity(0, 0), master);
        assertEquals(oldEnergy + otherEnergy, master.getEnergy());
        assertEquals(beastBiteCounter-1,((BadBeast)other).getBiteCounter());
    }

    @Test
    public void Master_Mini(){
        Board board = new Board(5,5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5,5, board);
        board.setNewMasterSquirrel(0,0, HandOperatedMasterSquirrel.class);
        Entity master = board.getEntity(0,0);

        board.setNewMiniSquirrel(1,1, 250,new HandOperatedMasterSquirrel(0,1000,new XY(2,2)));
        Entity other = board.getEntity(1,1);
        int oldEnergy= master.getEnergy();
        int otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MasterSquirrel) master,new XY(1,1));
        assertEquals(board.getEntity(1,1), master);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, master.getEnergy());
    }

    @Test
    public void Master_Master(){
        Board board = new Board(5,5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5,5, board);
        board.setNewMasterSquirrel(0,0, HandOperatedMasterSquirrel.class);
        Entity master = board.getEntity(0,0);

        board.setNewMasterSquirrel(1,1, HandOperatedMasterSquirrel.class);
        Entity other = board.getEntity(1,1);
        int oldEnergy= master.getEnergy();
        int otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MasterSquirrel) master,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), master);
        assertEquals(oldEnergy, master.getEnergy());
        assertEquals(otherEnergy, other.getEnergy());
    }

    @Test
    public void GoodBeastMaster(){
        Board board = new Board(3,3);
        FlattenedBoard flattenedBoard = new FlattenedBoard(3,3, board);
        board.setNewEntity(0,0,EntityType.GOOD_BEAST);
        Entity goodBeast = board.getEntity(0,0);

        board.setNewMasterSquirrel(1,1, HandOperatedMasterSquirrel.class);
        Entity other = board.getEntity(1,1);
        int oldEnergy= other.getEnergy();
        flattenedBoard.tryMove((GoodBeast) goodBeast,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertNotEquals(board.getEntity(0,0), goodBeast);
        assertNotEquals(oldEnergy, other.getEnergy());

    }

    @Test
    public void GoodBeastMini(){
        Board board = new Board(3,3);
        FlattenedBoard flattenedBoard = new FlattenedBoard(3,3, board);
        board.setNewEntity(0,0,EntityType.GOOD_BEAST);
        Entity goodBeast = board.getEntity(0,0);

        board.setNewMiniSquirrel(1,1, 50,new HandOperatedMasterSquirrel(0,1000,new XY(2,2)));
        Entity other = board.getEntity(1,1);
        int oldEnergy= other.getEnergy();
        flattenedBoard.tryMove((GoodBeast) goodBeast,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertNotEquals(board.getEntity(0,0), goodBeast);
        assertNotEquals(oldEnergy, other.getEnergy());

    }

    @Test
    public void GoodBeastPlant_Wall_Beast(){
        Board board = new Board(2,2);
        FlattenedBoard flattenedBoard = new FlattenedBoard(2,2, board);
        board.setNewEntity(0,0,EntityType.GOOD_BEAST);
        Entity goodBeast = board.getEntity(0,0);

        board.setNewEntity(1,1, EntityType.GOOD_BEAST);
        Entity other = board.getEntity(1,1);
        GoodBeastPlant_Wall_Beast_Helper(goodBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.BAD_BEAST);
        other = board.getEntity(1,1);
        GoodBeastPlant_Wall_Beast_Helper(goodBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.GOOD_PLANT);
        other = board.getEntity(1,1);
        GoodBeastPlant_Wall_Beast_Helper(goodBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.BAD_PLANT);
        other = board.getEntity(1,1);
        GoodBeastPlant_Wall_Beast_Helper(goodBeast,other,flattenedBoard,board);

        board.setNewEntity(1,1, EntityType.WALL);
        other = board.getEntity(1,1);
        GoodBeastPlant_Wall_Beast_Helper(goodBeast,other,flattenedBoard,board);
    }

    private void GoodBeastPlant_Wall_Beast_Helper(Entity goodBeast, Entity other, FlattenedBoard flattenedBoard, Board board){
        int oldEnergy= other.getEnergy();
        flattenedBoard.tryMove((GoodBeast)goodBeast,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), goodBeast);
        assertEquals(oldEnergy, other.getEnergy()); //Energie bleibt gleich, da keine Kollisonsregeln verfügbar
        board.removeEntity(other);
    }

    @Test
    public void Mini_Master(){
        Board board = new Board(5,5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5,5, board);
        board.setNewMiniSquirrel(0,0, 250,new HandOperatedMasterSquirrel(0,1000,new XY(2,2)));
        Entity mini = board.getEntity(0,0);

        board.setNewMasterSquirrel(1,1, HandOperatedMasterSquirrel.class);
        Entity other = board.getEntity(1,1);
        flattenedBoard.tryMove((MiniSquirrel) mini,new XY(1,1));
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), null);
    }

    @Test
    public void Mini_Mini() {
        Board board = new Board(5, 5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5, 5, board);
        MasterSquirrel master = new HandOperatedMasterSquirrel(0, 1000, new XY(2, 2));
        board.setNewMiniSquirrel(0, 0, 250, master);
        Entity mini = board.getEntity(0, 0);

        board.setNewMiniSquirrel(1, 1, 250, new HandOperatedMasterSquirrel(0, 1000, new XY(2, 3)));
        Entity other = board.getEntity(1, 1);
        flattenedBoard.tryMove((MiniSquirrel) mini, new XY(1, 1));

        assertEquals(board.getEntity(1, 1), null);
        assertEquals(board.getEntity(0, 0), null);

        board.setNewMiniSquirrel(0, 0, 250, master);
        mini = board.getEntity(0, 0);
        board.setNewMiniSquirrel(1, 1, 250, master);
        other = board.getEntity(1, 1);
        flattenedBoard.tryMove((MiniSquirrel) mini, new XY(1, 1));

        assertEquals(board.getEntity(1, 1), other);
        assertEquals(board.getEntity(0, 0), mini);
    }

    @Test
    public void Mini_Wall(){
        Board board = new Board(5, 5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5, 5, board);
        MasterSquirrel master = new HandOperatedMasterSquirrel(0, 1000, new XY(2, 2));
        board.setNewMiniSquirrel(0, 0, 19, master);
        Entity mini = board.getEntity(0, 0);

        board.setNewEntity(1,1, EntityType.WALL);
        Entity other = board.getEntity(1,1);
        int oldEnergy = mini.getEnergy();
        flattenedBoard.tryMove((MiniSquirrel)mini,new XY(1,1));
        assertEquals(oldEnergy-10,mini.getEnergy());
        assertEquals(board.getEntity(1, 1), other);
        assertEquals(board.getEntity(0, 0), mini);

        mini.updateEnergy(-10);
        flattenedBoard.tryMove((MiniSquirrel)mini,new XY(1,1));
        assertEquals(0,mini.getEnergy());
        assertEquals(board.getEntity(1, 1), other);
        assertEquals(board.getEntity(0, 0), null);

    }

    @Test
    public void Mini_Plant_GoodBeast(){
        Board board = new Board(5,5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5,5, board);
        MasterSquirrel master = new HandOperatedMasterSquirrel(0, 1000, new XY(2, 2));
        board.setNewMiniSquirrel(0, 0, 300, master);
        Entity mini = board.getEntity(0, 0);

        board.setNewEntity(1,1, EntityType.GOOD_PLANT);
        Entity other = board.getEntity(1,1);
        int oldEnergy= mini.getEnergy();
        int otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MiniSquirrel) mini,new XY(1,1));
        assertEquals(board.getEntity(1,1), mini);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, mini.getEnergy());

        board.moveEntity(mini, new XY(0,0));
        board.setNewEntity(1,1, EntityType.BAD_PLANT);
        other = board.getEntity(1,1);
        oldEnergy= mini.getEnergy();
        otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MiniSquirrel) mini,new XY(1,1));
        assertEquals(board.getEntity(1,1), mini);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, mini.getEnergy());

        board.moveEntity(mini, new XY(0,0));
        board.setNewEntity(1,1, EntityType.GOOD_BEAST);
        other = board.getEntity(1,1);
        oldEnergy= mini.getEnergy();
        otherEnergy = other.getEnergy();
        flattenedBoard.tryMove((MiniSquirrel) mini,new XY(1,1));
        assertEquals(board.getEntity(1,1), mini);
        assertEquals(board.getEntity(0,0), null);
        assertEquals(oldEnergy+otherEnergy, mini.getEnergy());

    }

    @Test
    public void Mini_BadBeast(){
        Board board = new Board(5, 5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5, 5, board);
        MasterSquirrel master = new HandOperatedMasterSquirrel(0, 1000, new XY(2, 2));
        board.setNewMiniSquirrel(0, 0, 500, master);
        Entity mini = board.getEntity(0, 0);

        board.setNewEntity(1, 1, EntityType.BAD_BEAST);
        Entity other = board.getEntity(1, 1);
        int oldEnergy = mini.getEnergy();
        int otherEnergy = other.getEnergy();
        int beastBiteCounter = ((BadBeast)other).getBiteCounter();
        flattenedBoard.tryMove((MiniSquirrel) mini, new XY(1, 1));
        assertEquals(board.getEntity(1, 1), other);
        assertEquals(board.getEntity(0, 0), mini);
        assertEquals(oldEnergy + otherEnergy, mini.getEnergy());
        assertEquals(beastBiteCounter-1,((BadBeast)other).getBiteCounter());
    }

    @Test
    public void Kill(){
        Board board = new Board(5,5);
        FlattenedBoard flattenedBoard = new FlattenedBoard(5,5, board);
        board.setNewEntity(0,0, EntityType.BAD_BEAST);
        Entity entity = board.getEntity(0,0);
        flattenedBoard.kill(entity);
        assertEquals(board.getEntity(0,0),null);
    }
}