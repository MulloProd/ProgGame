import java.util.Random;

public final class XY {
    public final int X;
    public final int Y;

    public XY(int x, int y) {
        X = x;
        Y = y;
    }

    public XY ADD(XY vector){
        return new XY(X+vector.X, Y+vector.Y);
    }

    public static XY RandomVector(){
        XY temp = new XY(new Random().nextInt(1)-1, new Random().nextInt(1)-1);
        return temp;
    }
}
