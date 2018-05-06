package fatsquirrel.CommandScanner;

//musst neu implementieren für aufgabe 2 -> siehe "fatsquirrel.CommandScanner.MyFavoriteCommandType"
public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class[] getParamTypes();
}
