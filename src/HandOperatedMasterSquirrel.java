import java.io.IOException;

public class HandOperatedMasterSquirrel extends MasterSquirrel {

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public void nextStep() throws IOException {

        while(true){
            char op = (char)System.in.read();
            System.in.read(new byte[System.in.available()]);

            switch (op){
                case '4':
                    toString("LEFT");
                    position = position.ADD(new XY(-1,0));
                    return;
                case '6':
                    toString("RIGHT");
                    position = position.ADD(new XY(1,0));
                    return;
                case '8':
                    toString("UP");
                    position = position.ADD(new XY(0,1));
                    return;
                case '2':
                    toString("DOWN");
                    position = position.ADD(new XY(0,-1));
                    return;
                default:
                    System.out.println("Keine Richtung ausgewählt!\nBitte erneut auswählen.");
                    break;
            }
        }


    }

    private static void toString(String direction){
        System.out.println("\nMastersquirrel turns: " + direction + "\n");
    }

}
