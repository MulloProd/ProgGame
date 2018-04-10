public class Board {
    private EntitySet entities;
    private final int height;
    private final int width;

    public Board(int x, int y){
        entities = new EntitySet(50);
        height = y;
        width = x;
    }
}
