package fatsquirrel;

import java.util.Random;

public class XY {
    public final int x;
    public final int y;



    public static final XY ZERO_ZERO = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

    public XY(int x, int y) { this.x = x;this.y =y; }

    public XY plus(XY xy) { return new XY(x +xy.x, y +xy.y); }

    public XY minus(XY xy) { return new XY(x -xy.x, y -xy.y); }

    public XY times(int factor) { return new XY(x *factor, y *factor); }

    public double length() { return Math.sqrt(x * x + y * y); }

    /**
     *
     * @param xy a second coordinate pair
     * @return the euklidian distance (pythagoras)
     */
    public double distanceFrom(XY xy) {
        XY temp = xy.minus(this);
        return temp.length();
    }

    public int hashCode() { return this.hashCode();}
        public boolean equals(Object obj) {
        if(obj instanceof XY && ((XY)obj).x == x && ((XY)obj).y == y)
            return true;
        else
            return false;
    }

    public static XY randomVector(){
        XY temp = new XY(new Random().nextInt(3)-1, new Random().nextInt(3)-1);
        return temp;
    }

    public String toString() { return ("("+ x +"/"+ y +")"); }

}