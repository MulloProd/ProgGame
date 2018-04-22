package fatsquirrel;

import fatsquirrel.Console.MoveCommand;
import fatsquirrel.core.BoardView;

import java.io.IOException;

public interface UI {

    void render(BoardView view);
    MoveCommand getCommand() throws IOException;
}
