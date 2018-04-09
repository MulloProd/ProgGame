public class MasterSquirrel extends Entity{

    public MasterSquirrel(int id, int energy, XY position) {
        super(id, 1000, position);
    }

    public boolean checkSquirrel(Entity entity){
        return entity instanceof MiniSquirrel;
    }

    public void sendEnergy(Entity entity, int miniEnergy){
        if(checkSquirrel(entity)){
            MiniSquirrel miniSquirrel = (MiniSquirrel) entity;

            //(miniSquirrel.getEnergie()+ energie>=100)?MiniSquirrel.setEnergie(100):MiniSquirrel.setEnergie(MiniSquirrel.energie + energie);

            if(miniSquirrel.getEnergy()+ miniEnergy>=100){
                miniSquirrel.setEnergy(100);
            }
            else{
                miniSquirrel.setEnergy(miniSquirrel.energy + miniEnergy);
            }

            energy -= energy - miniEnergy;
        }
    }

    @Override
    public void nextStep() {

    }

}


