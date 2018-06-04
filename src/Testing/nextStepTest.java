package Testing;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.BadBeast;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.GoodBeast;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.jmock.internal.Cardinality.exactly;
import static org.junit.Assert.assertTrue;

public class nextStepTest {
    Mockery context = new Mockery();
    EntityContext entityContext = context.mock(EntityContext.class);
    Entity entity = new BadBeast(0,100,XY.ZERO_ZERO);

    @Before
    public void befor(){
        context.checking(new Expectations() {{
            atMost(1).of (entityContext).tryMove(with(any(BadBeast.class)),with(any(XY.class)));
            atMost(1).of (entityContext).tryMove(with(any(GoodBeast.class)),with(any(XY.class)));
            atMost(1).of (entityContext).tryMove(with(any(MasterSquirrel.class)),with(any(XY.class)));
            atMost(1).of (entityContext).tryMove(with(any(MiniSquirrel.class)),with(any(XY.class)));
            allowing (entityContext).nearestPlayerEntity(with(any(XY.class)));
                will(returnValue(null));
        }
        });
    }

    @Test
    public void nextStepBadBeast() {
        BadBeast badBeast = new BadBeast(0, 100, XY.ZERO_ZERO);

        XY startpos = badBeast.getPosition();
        badBeast.nextStep(entityContext);
        assertEquals(startpos, badBeast.getPosition());
        badBeast.nextStep(entityContext);
        assertEquals(startpos, badBeast.getPosition());
        badBeast.nextStep(entityContext);
        assertEquals(startpos, badBeast.getPosition());
        badBeast.nextStep(entityContext);
        assertNotEquals(startpos, badBeast.getPosition());
    }
}
