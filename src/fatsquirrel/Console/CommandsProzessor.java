package fatsquirrel.Console;//Beispielimplementation um den commandScanner zu verwenden, wird später gelöscht


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CommandsProzessor {
    PrintStream outputStream = System.out;
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public void process() throws IOException {
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
                        break;
                    case UP:
                        break;
                    case DOWN:
                        break;
                    case RIGHT:
                        break;
                    case MASTER_ENERGY:
                        break;
                    case SPAWN_MINI:
                        outputStream.println((int)command.getParams()[0]);
                        break;
                }
            }
        }
    }
    private void help(){
        outputStream.println("worked");
    }
}
