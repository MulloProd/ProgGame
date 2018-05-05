package fatsquirrel.Console;


//Rückgabe vom commandScanner
public class Command {
    private final CommandTypeInfo commandType;
    private final Object[] params;

    public Command(CommandTypeInfo commandType, Object[] params){
        this.commandType = commandType;
        this.params = params;
    }


    public CommandTypeInfo getCommandType() {
        return commandType;
    }

    public Object[] getParams() {
        return params;
    }
}
