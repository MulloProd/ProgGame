import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        Random random = new Random();

        EntitySet entitySet = new EntitySet(20);

        MasterSquirrel masterSquirrel = new HandOperatedMasterSquirrel(0, 1000, new XY(random.nextInt(50), random.nextInt(50)));
        MiniSquirrel miniSquirrel = new MiniSquirrel(1, 50, new XY(random.nextInt(50), random.nextInt(50)), masterSquirrel);
        MiniSquirrel miniSquirrel2 = new MiniSquirrel(2, 50, new XY(random.nextInt(50), random.nextInt(50)), masterSquirrel);
        GoodPlant goodPlant = new GoodPlant(3, 20, new XY(random.nextInt(50), random.nextInt(50)));
        GoodPlant goodPlant2 = new GoodPlant(4, 10, new XY(random.nextInt(50), random.nextInt(50)));
        BadPlant badPlant = new BadPlant(5, 20, new XY(random.nextInt(50), random.nextInt(50)));

        entitySet.addEntity(masterSquirrel);
        entitySet.addEntity(miniSquirrel);
        entitySet.addEntity(miniSquirrel2);
        entitySet.addEntity(goodPlant);
        entitySet.addEntity(goodPlant2);
        entitySet.addEntity(badPlant);

        System.out.println(entitySet.toString());
        entitySet.deleteEntity(miniSquirrel2);

        System.out.println(entitySet.toString());
        miniSquirrel.setEnergy(20);

        System.out.println(entitySet.toString());
        masterSquirrel.sendEnergy(miniSquirrel, 80);

        System.out.println(entitySet.toString());
        masterSquirrel.nextStep();

        System.out.println(entitySet.toString());

        while(true){

            char op = (char)System.in.read();
            System.in.read(new byte[System.in.available()]);

            if(op == '\r' || op == '\n')
                entitySet.nextStep();


            if(masterSquirrel.testTile() != null){
                Entity other = masterSquirrel.testTile();
                switch(other.getClass().getSimpleName()){
                    case "GoodPlant":
                        masterSquirrel.AddEnergy(other.getEnergy());
                        break;
                    case "BadPlant":
                        masterSquirrel.AddEnergy(-other.getEnergy());
                        break;
                    case "GoodBeast":
                        masterSquirrel.AddEnergy(other.getEnergy());
                        break;
                    case "BadBeast":
                        masterSquirrel.AddEnergy(-other.getEnergy());
                        break;
                }
            }
        }
    }
}
