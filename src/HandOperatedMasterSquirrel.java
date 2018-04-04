import java.io.IOException;

public abstract class HandOperatedMasterSquirrel extends MasterSquirrel {

    public HandOperatedMasterSquirrel(int id, int energy, XY position) {
        super(id, energy, position);
    }

    public static void catchOperation() throws IOException {

        while(true){
            char op = (char)System.in.read();
            System.in.read(new byte[System.in.available()]);

            switch (op){
                case '4':
                    setOperation("LEFT");
                    return;
                case '6':
                    setOperation("RIGHT");
                    return;
                case '8':
                    setOperation("TOP");
                    return;
                case '2':
                    setOperation("DOWN");
                    return;
                default:
                    System.out.println("Keine Richtung ausgewählt!\nBitte erneut auswählen.");
                    break;
            }
        }


    }

    private static void setOperation(String direction){
        System.out.println(direction);
    }

}
