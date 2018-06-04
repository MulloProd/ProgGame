package Testing;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.BadBeast;
import org.junit.Test;
import static org.junit.Assert.*;

public class BadBeastTest {

    BadBeast badBeast =new BadBeast(0,100, XY.ZERO_ZERO);

    @Test
    public void decreaseBiteCounter() throws Exception {
        int i=badBeast.getBiteCounter();
        badBeast.decreaseBiteCounter();
        assertEquals(i-1,badBeast.getBiteCounter());
    }
}
