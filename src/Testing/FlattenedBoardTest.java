package Testing;

import fatsquirrel.Logging;
import fatsquirrel.XY;
import fatsquirrel.core.Board;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;
import fatsquirrel.core.FlattenedBoard;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlattenedBoardTest {

    Mockery context = new Mockery();
    final Board board = context.mock(Board.class);

    private final FlattenedBoard flattenedBoard = new FlattenedBoard(15,15, board);

    @Test
    public void getSize() {
    }

    @Test
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {
    }

    @Test
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {
    }

    @Test
    public void tryMove(BadBeast badBeast, XY moveDirection) {
    }

    @Test
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {
    }

    @Test
    public void nearestPlayerEntity(XY pos) {
    }

    @Test
    public void kill(Entity entity) {
    }

    @Test
    public void killAndReplace(Entity entity) {
    }

    @Test
    public void getEntityType(XY xy) {
    }

    @Test
    public void getEntityType(int x, int y) {
    }

    @Test
    public void spawn_Mini(XY direction, int energy, MasterSquirrel masterSquirrel) {
    }

    @Test
    public void allEntitiesToString() {
    }

    @Test
    public void getEntityAt(int x, int y) {
    }
}