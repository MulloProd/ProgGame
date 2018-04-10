public class MasterSquirrel extends Entity{

    public MasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public boolean checkSquirrel(Entity entity){
        return entity instanceof MiniSquirrel;
    }

    public void sendEnergy(Entity entity, int miniEnergy){
        if(checkSquirrel(entity)){
            MiniSquirrel miniSquirrel = (MiniSquirrel) entity;

            int difference = miniSquirrel.AddEnergy(miniEnergy);

            if(difference>0) {
                AddEnergy(-(miniEnergy-difference));
                toString("Mastersquirrel übergibt " + (miniEnergy-difference) + " Energie an Minisquirrel.\n");
            }
            else {
                AddEnergy(-miniEnergy);
                toString("Mastersquirrel übergibt " + miniEnergy + " Energie an Minisquirrel.\n");
            }
        }
    }

    @Override
    public void nextStep() {

    }

    private static void toString(String string){
        System.out.println(string);
    }

}


