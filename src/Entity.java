public abstract class Entity {
    private final int ID;
    private int energy;
    private final int startEnergy;
    private XY position;

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
        if(newEnergy < startEnergy)
            energy = newEnergy;
        else
            energy = newEnergy;
    }

    public void AddEnergy(int value){
        if(energy + value < startEnergy)
            energy += value;
        else
            energy = startEnergy;
    }

    public int getID() {
        return ID;
    }

    public String toString(){
        return this.getClass().getSimpleName() + ", Energy: " + energy + "/" + startEnergy + ", Pos: " + position.X + "/" + position.Y;
    }
}
