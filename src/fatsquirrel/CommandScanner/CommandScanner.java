package fatsquirrel.CommandScanner;/*
Vorgeheinsweise:
1.Kommandoname eingeben und mit Enter bestätigen
2.(optional)Paramenter eingeben und mit Enter bestätigen

Falls Kommando parameter erwartet und anschließend ein kommando eingegeben wird,
    wird dieses nicht ausgeführt, da parameter erwartet wird
Evtl später ändern das Kommando später mit Parameter in einer zeile eingelesen wird und mit leerzeichen getrennt wird.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {
    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream = System.out;

    public Command next() throws IOException {
        String input = inputReader.readLine();

        //Testen ob input inem kommandoname entspricht
        for (int i = 0; i < commandTypeInfos.length; i++) {
            if (input.equals(commandTypeInfos[i].getName())) {
                Object[] params = new Object[commandTypeInfos[i].getParamTypes().length];

                //schauen ob parameter stimmen <-- wird noch bisschen überarbeitete wegen fehleranfälligkeit, aber wenn man alles richtig eingibt klappts
                for (int e = 0; e < commandTypeInfos[i].getParamTypes().length;e++) {
                    String parInput = inputReader.readLine();
                    if(commandTypeInfos[i].getParamTypes()[e] == int.class){
                        try {
                            params[e] = Integer.parseInt(parInput);
                        }
                        catch(Exception ex){
                            return null;
                        }
                    }
                    else if(commandTypeInfos[i].getParamTypes()[e] == float.class){
                        try {
                            params[e] = Float.parseFloat(parInput);
                        }
                        catch(Exception ex){
                            return null;}
                    }
                    else if(commandTypeInfos[i].getParamTypes()[e] == String.class){
                        params[e] = parInput;
                    }
                    else
                        return null;
                }
                return new Command(commandTypeInfos[i], params);
            }
        }
        return null;
    }

    CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader){
        this.commandTypeInfos = commandTypes;
        this.inputReader = inputReader;
    }
}
