package Testing;
import static org.junit.Assert.*;

import com.sun.org.apache.xpath.internal.operations.Equals;
import fatsquirrel.XY;
import org.junit.After;
import org.junit.Test;

public class XYTest {

    private final XY xy1 = new XY(1,2);
    private final XY xy2 = new XY(3,4);
    @Test
    public void equals() throws Exception {
        assertFalse(xy1.equals(xy2));
        assertTrue(xy1.equals(xy1));
        assertTrue(xy1.equals(new XY(1,2)));
    }

    @Test
    public void Plus(){
        assertEquals("X:1+3=4 / Y:2+4=6",new XY(4,6), xy1.plus(xy2));
    }
    @Test
    public void Minus(){
        assertEquals("X:1-3=-2 / Y:2-4=-2",new XY(-2,-2), xy1.minus(xy2));
        assertEquals("X:3-1=2 / Y:4-2=2",new XY(2,2), xy2.minus(xy1));
    }
    @Test
    public void Times(){
        assertEquals("X:1*5=5 / Y:2*5=10",new XY(5,10), xy1.times(5));
    }
    @Test
    public void Length(){
        assertEquals(Math.sqrt(5), xy1.length(), 0);
        assertEquals(Math.sqrt(5), xy1.length(), 0);
    }
    @Test
    public void DistanceFrom(){
        assertEquals(Math.sqrt(8), xy1.distanceFrom(xy2),0);
    }
}
