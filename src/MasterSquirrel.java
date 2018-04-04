public abstract class MasterSquirrel extends Entity{

    public static int energie;

    public MasterSquirrel(int id, int energy, XY position) {
        super(id, 1000, position);
    }

    public boolean checkSquirrel(Entity entity){
        return entity instanceof MiniSquirrel;
    }

    public static void setEnergie(int newEnergie){
        energie = newEnergie;
    }

    public int getEnergie(){
        return energie;
    }

    public void sendEnergie(Entity entity, int energie){
        if(checkSquirrel(entity)){
            MiniSquirrel miniSquirrel = (MiniSquirrel) entity;

            //(miniSquirrel.getEnergie()+ energie>=100)?MiniSquirrel.setEnergie(100):MiniSquirrel.setEnergie(MiniSquirrel.energie + energie);

            if(miniSquirrel.getEnergie()+ energie>=100){
                MiniSquirrel.setEnergie(100);
            }
            else{
                MiniSquirrel.setEnergie(MiniSquirrel.energie + energie);
            }
        }
    }

}


