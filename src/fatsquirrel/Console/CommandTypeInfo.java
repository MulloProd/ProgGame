package fatsquirrel.Console;

public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class[] getParamTypes();
}
