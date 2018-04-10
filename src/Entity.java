public abstract class Entity {
    private final int ID;
    private int energy;
    protected final int startEnergy;
    protected XY position;

    public Entity(int id, int energy, XY position) {
        ID = id;
        startEnergy = energy;
        this.energy = energy;
        this.position = position;
    }

    public abstract void nextStep();

    public XY getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int newEnergy){
        energy = newEnergy;
        toString("Die neue Energie von " + this.getClass().getSimpleName() + " beträgt " + newEnergy + ".\n");
        if(newEnergy < startEnergy)
            energy = newEnergy;
        else
            energy = newEnergy;
    }

    public int getID() {
        return ID;

    }

    public int AddEnergy(int value){
        if(energy + value < startEnergy) {
            energy += value;
            return 0;
        }
        else{
            int difference = energy + value - startEnergy;
            energy = startEnergy;
            return difference;
        }
    }

    public String toString(){
        return this.getClass().getSimpleName() + ", Energy: " + energy + "/" + startEnergy + ", Pos: " + position.X + "/" + position.Y;
    }

    private static void toString(String string){
        System.out.println(string);
    }
}
