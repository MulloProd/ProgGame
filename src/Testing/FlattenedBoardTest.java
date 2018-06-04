package Testing;

import fatsquirrel.XY;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;
import fatsquirrel.core.FlattenedBoard;
import org.jmock.Mockery;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class FlattenedBoardTest {

    Mockery context = new Mockery();
    //final BoardView boardView = context.mock(BoardView.class);

    private final Board board = new Board();
    private final FlattenedBoard flattenedBoard = new FlattenedBoard(15,15, board);
    private final MasterSquirrel masterSquirrel = new MasterSquirrelBot(0,1000,new XY(1,1));



    public FlattenedBoardTest() throws Exception {
    }

    @Test
    public void BadMeetsBeast(){
        
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
    public void spawn_Mini() {

        XY direction = new XY(1,0);
        int energy = 100;

        assertEquals("int x = 2",2, masterSquirrel.getPosition().x +direction.x);
        assertEquals("int y = 1", 1, masterSquirrel.getPosition().y +direction.y);

        flattenedBoard.spawn_Mini(direction,energy,masterSquirrel);

        //board.setNewMiniSquirrel(x, y, energy, masterSquirrel);
    }

    @Test
    public void allEntitiesToString() {
    }

    @Test
    public void getEntityAt() {
    }
}