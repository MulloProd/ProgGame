public abstract class Game {

    public void run(){
        while(true){
            render();
            processInput();
            update();
        }
    }

    private void update() {

    }

    public abstract void processInput();

    public abstract void render();
}
