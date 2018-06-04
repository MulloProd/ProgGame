package Testing;

import fatsquirrel.XY;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.BadBeast;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.PlayerEntities.HandOperatedMasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;
import fatsquirrel.core.FlattenedBoard;
import org.jmock.Mockery;
import org.junit.Test;

import java.io.IOException;

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
        flattenedBoard.tryMove((BadBeast)badBeast,new XY(1,1));
        int oldEnergy= other.getEnergy();
        int oldBadBeastCounter = ((BadBeast)badBeast).getBiteCounter();
        assertEquals(board.getEntity(1,1), other);
        assertEquals(board.getEntity(0,0), badBeast);
        assertEquals(oldEnergy, other.getEnergy());
        assertEquals(oldBadBeastCounter, ((BadBeast) badBeast).getBiteCounter());
        board.removeEntity(other);

    }
    @Test
    public void BadBeastPlayer() {
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
        assertNotEquals(oldBadBeastCounter, ((BadBeast) badBeast).getBiteCounter());
    }

    @Test
    public void getSize() {
    }

    @Test
    public void tryMove() {
    }

    @Test
    public void tryMove1() {
    }

    @Test
    public void tryMove2() {
    }

    @Test
    public void tryMove3() {
    }

    @Test
    public void nearestPlayerEntity() {
    }

    @Test
    public void kill() {
    }

    @Test
    public void killAndReplace() {
    }

    @Test
    public void getEntityType() {
    }

    @Test
    public void getEntityType1() {
    }

    @Test
    public void allEntitiesToString() {
    }

    @Test
    public void getEntityAt() {
    }
}