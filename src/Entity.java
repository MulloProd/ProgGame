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

    public int getID() {
        return ID;
    }

    public void UpdateEnergy(int value){
        energy += value;
    }

    public String toString(){
        if (this instanceof MiniSquirrel){
            return "MiniSquirrel";
        }
        else if(this instanceof MasterSquirrel){
            return "MasterSquirrel";
        }

        return "test";
    }
}
