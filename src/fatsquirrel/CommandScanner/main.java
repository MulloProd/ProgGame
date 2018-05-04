package fatsquirrel.CommandScanner;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        CommandsProzessor commandsProzessor = new CommandsProzessor();
        commandsProzessor.process();
    }
}
