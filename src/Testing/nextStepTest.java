package Testing;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.Implementation.FirstMasterBotController;
import fatsquirrel.botapi.OutOfViewException;
import fatsquirrel.botapi.SpawnException;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrelBot;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.PlayerEntity;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.jmock.api.Expectation;
import org.jmock.internal.InvocationExpectation;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.junit.Assert.*;

import static org.jmock.internal.Cardinality.exactly;
import static org.junit.Assert.assertTrue;

public class nextStepTest {
    Mockery context = new Mockery();
    EntityContext entityContext = context.mock(EntityContext.class);
    ControllerContext controllerContext = context.mock(ControllerContext.class);


    @Test
    public void nextStepBadBeast() {
        context.checking(new Expectations() {{
        oneOf (entityContext).tryMove(with(any(BadBeast.class)),with(any(XY.class)));
        allowing (entityContext).nearestPlayerEntity(with(any(XY.class)));
        will(returnValue(null));
        }});

        BadBeast badBeast = new BadBeast(0, 100, XY.ZERO_ZERO);

        badBeast.nextStep(entityContext);
        badBeast.nextStep(entityContext);
        badBeast.nextStep(entityContext);
        badBeast.nextStep(entityContext);

        context.assertIsSatisfied();
    }
    @Test
    public void nextStepGoodBeast() {
        context.checking(new Expectations() {{
            oneOf (entityContext).tryMove(with(any(GoodBeast.class)),with(any(XY.class)));
            allowing (entityContext).nearestPlayerEntity(with(any(XY.class)));
            will(returnValue(null));
        }});

        GoodBeast goodBeast = new GoodBeast(0, 100, XY.ZERO_ZERO);

        goodBeast.nextStep(entityContext);
        goodBeast.nextStep(entityContext);
        goodBeast.nextStep(entityContext);
        goodBeast.nextStep(entityContext);

        context.assertIsSatisfied();
    }
    @Test
    public void nextStepFirstMasterBot() throws IOException, SpawnException, OutOfViewException {
        BotController botController = new FirstMasterBotController();

        context.checking(new Expectations() {{
            atMost(1).of (controllerContext).move(with(any(XY.class)));
            atMost(1).of (controllerContext).spawnMiniBot(with(any(XY.class)), with(any(int.class)));
            allowing (controllerContext).getViewLowerLeft();
            will(returnValue(XY.ZERO_ZERO));
            allowing (controllerContext).getViewUpperRight();
            will(returnValue(XY.ZERO_ZERO));
            allowing (controllerContext).locate();
                will(returnValue(XY.ZERO_ZERO));
            allowing (controllerContext).getEntityAt(with(any(XY.class)));
                will (returnValue(EntityType.NONE));
        }});
        botController.nextStep(controllerContext);
        context.assertIsSatisfied();
    }
    @Test
    public void nextStepMasterBot() throws IOException {
        MasterSquirrelBot masterSquirrelBot = new MasterSquirrelBot(0,100,XY.ZERO_ZERO);
        masterSquirrelBot.setCollisionCounter(1);
        context.checking(noMoveExpectation());
        masterSquirrelBot.nextStep(entityContext);
        context.assertIsSatisfied();
        context.checking(moveExpectation());
        masterSquirrelBot.nextStep(entityContext);
        context.assertIsSatisfied();
    }

    private Expectations moveExpectation() {
        return new Expectations() {{
            ignoring(entityContext).getSize();
            will(returnValue(new XY(2,2)));
            ignoring(entityContext).getEntityType(with(any(XY.class)));
            will(returnValue(EntityType.NONE));
            oneOf(entityContext).tryMove(with(any(MasterSquirrel.class)),with(any(XY.class)));
        }};
    }
    private Expectations noMoveExpectation() {
        return new Expectations() {{
            never(entityContext).tryMove(with(any(MasterSquirrel.class)),with(any(XY.class)));
            ignoring(entityContext).getSize();
            will(returnValue(new XY(2,2)));
            ignoring(entityContext).getEntityType(with(any(XY.class)));
            will(returnValue(EntityType.NONE));
        }};
    }
}
