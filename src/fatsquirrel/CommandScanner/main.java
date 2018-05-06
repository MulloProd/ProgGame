package fatsquirrel.CommandScanner;

import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        MyFavoriteCommandsProcessor myFavoriteCommandsProcessor = new MyFavoriteCommandsProcessor();
        myFavoriteCommandsProcessor.process();
    }
}
