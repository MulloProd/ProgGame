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

            miniSquirrel.AddEnergy(miniEnergy);

            AddEnergy(-miniEnergy);
        }
    }

    @Override
    public void nextStep() {

    }

}


