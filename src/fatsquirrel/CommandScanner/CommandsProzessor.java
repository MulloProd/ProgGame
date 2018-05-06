package fatsquirrel.CommandScanner;//Beispielimplementation um den commandScanner zu verwenden, wird später gelöscht
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CommandsProzessor {
    PrintStream outputStream = System.out;
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public void process() throws IOException {
        CommandScanner commandScanner = new CommandScanner(CommandType.values(), inputReader);

        while (true) { // the loop over all commands with one input line for every command

            Command command = commandScanner.next();

            if(command != null) {
                switch ((CommandType)command.getCommandType()) {
                    case EXIT:
                        System.exit(0);
                    case HELP:
                        help();
                        break;
                    case ADDI:
                        outputStream.println((int)command.getParams()[0] + (int)command.getParams()[1]);
                        break;
                    case ADDF:
                        outputStream.println((float)command.getParams()[0] + (float)command.getParams()[1]);
                        break;
                    case ECHO:
                        for(int i=0; i< (int)command.getParams()[1]; i++){
                            outputStream.println((String) command.getParams()[0]);
                        }
                        break;
                }
            }
        }
    }
    private void help(){
        for(CommandType commandType: CommandType.values()){
            outputStream.println("<" +commandType.getName() + "> - " + commandType.getHelpText());
        }
    }
}
