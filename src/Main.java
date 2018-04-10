import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Random random = new Random();

        EntitySet entitySet = new EntitySet(20);

        MasterSquirrel masterSquirrel = new MasterSquirrel(
                0, 1000, new XY(random.nextInt(50), random.nextInt(50)));
        MiniSquirrel miniSquirrel = new MiniSquirrel(
                1, 50, new XY(random.nextInt(50), random.nextInt(50)), masterSquirrel);
        MiniSquirrel miniSquirrel2 = new MiniSquirrel(
                2, 50, new XY(random.nextInt(50), random.nextInt(50)), masterSquirrel);

        entitySet.addEntity(masterSquirrel);
        entitySet.addEntity(miniSquirrel);
        entitySet.addEntity(miniSquirrel2);

        System.out.println(entitySet.toString());

        entitySet.deleteEntity(miniSquirrel2);

        System.out.println(entitySet.toString());

        miniSquirrel.setEnergy(20);

        System.out.println(entitySet.toString());

        masterSquirrel.sendEnergy(miniSquirrel, 80);

        System.out.println(entitySet.toString());

        HandOperatedMasterSquirrel.catchOperation();

        System.out.println(entitySet.toString());
    }
}
