import java.io.IOException;

public abstract class MasterSquirrel extends Entity{

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

    public Entity testTile(){
        for(Entity e : EntitySet.set){
            if(e != null && e.position.X == position.X && e.position.Y == position.Y && !(e instanceof MasterSquirrel)){
                return e;
            }
        }

        return null;
    }


    private static void toString(String string){
        System.out.println(string);
    }

}


