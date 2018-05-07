package fatsquirrel.Console;

import fatsquirrel.UI;
import fatsquirrel.core.BoardView;

import java.io.IOException;

public class ConsoleUI implements UI {
    @Override
    public void render(BoardView view) {
        for(int y=0;y<view.getSize().Y;y++){
            for(int x=0;x<view.getSize().X;x++){
                switch (view.getEntityType(x,y).getType()){
                    case None:
                        System.out.print(" ");
                        break;
                    case Wall:
                        System.out.print("#");
                        break;
                    case BadBeast:
                        System.out.print("B");
                        break;
                    case GoodBeast:
                        System.out.print("b");
                        break;
                    case BadPlant:
                        System.out.print("X");
                        break;
                    case GoodPlant:
                        System.out.print("O");
                        break;
                    case HandOperatedMasterSquirrel:
                        System.out.print("M");
                        break;
                    case MiniSquirrel:
                        System.out.print("m");
                        break;
                }
            }
            System.out.println();
        }
    }

    @Override
    public MoveCommand getCommand() throws IOException {
        CommandsProzessor commandsProzessor = new CommandsProzessor();
        return commandsProzessor.process();
    }
}
