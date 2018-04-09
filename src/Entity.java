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
<<<<<<< HEAD
        energy = newEnergy;
        toString("Die neue Energie von " + this.getClass().getSimpleName() + " beträgt " + newEnergy + ".\n");
    }

    public int getID() {
        return ID;
=======
        if(newEnergy < startEnergy)
            energy = newEnergy;
        else
            energy = newEnergy;
>>>>>>> 52c1d1a71a927899be529fbac1f44c06765cfa0a
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

    public int getID() {
        return ID;
    }

    public String toString(){
        return this.getClass().getSimpleName() + ", Energy: " + energy + "/" + startEnergy + ", Pos: " + position.X + "/" + position.Y;
    }

    private static void toString(String string){
        System.out.println(string);
    }
}
