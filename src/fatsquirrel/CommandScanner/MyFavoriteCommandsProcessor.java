package fatsquirrel.CommandScanner;//Beispielimplementation um den commandScanner zu verwenden, wird später gelöscht
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Modifier;

public class MyFavoriteCommandsProcessor {
    PrintStream outputStream = System.out;
    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

    public void process() throws IOException {
        CommandScanner commandScanner = new CommandScanner(MyFavoriteCommandType.values(), inputReader);

        while (true) { // the loop over all commands with one input line for every command

            Command command = commandScanner.next();

            if(command != null) {
                switch ((MyFavoriteCommandType)command.getCommandType()) {
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
                    case REFACTOR:
                        writeClassHeader(MyFavoriteCommandsProcessor.class);
                }
            }
        }
    }
    private void help(){
        for(MyFavoriteCommandType commandType: MyFavoriteCommandType.values()){
            outputStream.println("<" +commandType.getName() + "> - " + commandType.getHelpText());
        }
    }

    private void addi(){
    }

    private static void writeClassHeader(Class c) {
        String name = c.getName();
        int i = name.lastIndexOf(".");
        if (i > -1) { // extract package name
            System.out.println("package "+ name.substring(0,i) + ";");
            name = name.substring(i+1, name.length());
        }

        System.out.print(Modifier.toString(c.getModifiers()) + " ");
        System.out.print("class " + name + " ");

        Class superclass = c.getSuperclass();
        if (superclass != null)
            System.out.print("extends " + superclass.getName() + " ");

        Class[] interfaces = c.getInterfaces();
        if (interfaces.length != 0) {
            System.out.print("implements ");
            for (int j=0; ; j++) {
                System.out.print(interfaces[j].getName());
                if (j == (interfaces.length -1)) break;
                else System.out.print(", ");
            }
        }
    }
}
