package fatsquirrel.Console;//Beispielimplementation um den commandScanner zu verwenden, wird später gelöscht


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CommandsProzessor {
    PrintStream outputStream = System.out;
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public MoveCommand process() throws IOException {
        CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);

        while (true) { // the loop over all commands with one input line for every command

            Command command = commandScanner.next();

            if(command != null) {
                switch ((GameCommandType)command.getCommandType()) {
                    case EXIT:
                        System.exit(0);
                    case HELP:
                        help();
                        break;
                    case ALL:
                        break;
                    case LEFT:
                        return move(-1,0);
                    case UP:
                        return move(0,-1);
                    case DOWN:
                        return move(0,1);
                    case RIGHT:
                        return move(1,0);
                    case MASTER_ENERGY:
                        break;
                    case SPAWN_MINI:
                        try{
                            return spawn_mini((int)command.getParams()[0]);
                        }
                        catch (NotEnoughEnergyException e){
                            System.out.println("MasterSquirrel besitzt nicht genügend Energie.");
                        }

                }
            }
        }
    }
    private void help(){
        for(GameCommandType commandType: GameCommandType.values()){
            outputStream.println("<" +commandType.getName() + "> - " + commandType.getHelpText());
        }
    }

    private MoveCommand move(int x, int y){
        return new MoveCommand(x,y, 0);
    }

    private MoveCommand spawn_mini (int miniEnergy) throws NotEnoughEnergyException {
        //Position und Energy von MasterSquirrel holen und Random "davor" setzen

        //Wenn abzugebende Energie größer der eigenen ist -> Exception
        if(miniEnergy>200)
            throw new NotEnoughEnergyException("Mastersquirrel besitzt nur noch " + 200 + " Energie!");

        int x =0;
        int y= 0;
        return new MoveCommand(x,y, miniEnergy);
    }
}


