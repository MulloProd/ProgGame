package fatsquirrel.CommandScanner;

//musst neu implementieren für aufgabe 2 -> siehe "fatsquirrel.CommandScanner.CommandType"
public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class[] getParamTypes();
}
