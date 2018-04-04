import java.util.Random;

public class Main {

    public static void main(String[] args){

        Random random = new Random();

        EntitySet entitySet = new EntitySet(20);

        MasterSquirrel masterSquirrel = new MasterSquirrel(
                0, 1000, new XY(random.nextInt(50), random.nextInt(50))) {
            @Override
            public void nextStep() {

            }
        };
        MiniSquirrel miniSquirrel = new MiniSquirrel(
                1, 50, new XY(random.nextInt(50), random.nextInt(50))) {
            @Override
            public void nextStep() {

            }
        };
        MiniSquirrel miniSquirrel2 = new MiniSquirrel(
                2, 50, new XY(random.nextInt(50), random.nextInt(50))) {
            @Override
            public void nextStep() {

            }
        };

        entitySet.addEntity(masterSquirrel);
        entitySet.addEntity(miniSquirrel);
        entitySet.deleteEntity(miniSquirrel2);

        System.out.println(entitySet.toString());


        //HandOperatedMasterSquirrel.catchOperation();
    }
}
