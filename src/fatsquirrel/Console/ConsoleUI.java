package fatsquirrel.Console;

import fatsquirrel.UI;
import fatsquirrel.core.BoardView;

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
                        System.out.print("P");
                        break;
                    case GoodPlant:
                        System.out.print("o");
                        break;
                    case HandOperatedMasterSquirrel:
                        System.out.print("M");
                        break;
                    case MasterSquirrel:
                        System.out.print("S");
                        break;
                    case MiniSquirrel:
                        System.out.print("s");
                        break;

                }
            }
            System.out.println();
        }

    }
}
