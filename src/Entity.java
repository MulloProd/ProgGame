public abstract class Entity {
    protected final int ID;
    protected int energy;
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
    }

    public int getID() {
        return ID;
    }

    public void AddEnergy(int value){
        if(energy + value < startEnergy)
            energy += value;
        else
            energy = startEnergy;
    }

    public String toString(){
        return this.getClass().getSimpleName() + ", Energy: " + energy + "/" + startEnergy + ", Pos: " + position.X + "/" + position.Y;
    }
}
