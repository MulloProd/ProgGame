package fatsquirrel.UIs;

import fatsquirrel.Game.GameCommandType;
import fatsquirrel.Console.MoveCommand;
import fatsquirrel.XY;
import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.BoardView;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class FxUI extends Scene implements UI {

    private Canvas boardCanvas;
    private Label msgLabel;
    private static final int CELL_SIZE = 50;
    private static MoveCommand moveCommand;
    private static Label statusLabel;
    private static String[] botNames = BoardConfig.getBotNames();
    private static int step = 0;
    private static int round = 1;
    private static int maxSteps = BoardConfig.getSteps();
    private static int maxRounds = BoardConfig.getRounds();


    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }

    public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.x * CELL_SIZE, boardSize.y * CELL_SIZE);
        statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Spiel wird gestartet...");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
        fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Es wurde folgende Taste gedrückt: " + keyEvent.getCode() + ".");

                if(keyEvent.getCode().name().toUpperCase().equals(GameCommandType.LEFT.toString())){
                    moveCommand = new MoveCommand(-1,0,0,false,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals(GameCommandType.RIGHT.toString())){
                    moveCommand = new MoveCommand(1,0,0,false,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals(GameCommandType.UP.toString())){
                    moveCommand = new MoveCommand(0,-1,0,false,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals(GameCommandType.DOWN.toString())){
                    moveCommand = new MoveCommand(0,1,0,false,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals("A")){
                    moveCommand = new MoveCommand(0,0,0,true,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals("H")){
                    moveCommand = new MoveCommand(0,1,0,false,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals("S")){
                    moveCommand = new MoveCommand(0,0,100,false,false);
                }
                else if(keyEvent.getCode().name().toUpperCase().equals("M")){
                    moveCommand = new MoveCommand(0,0,0,false,true);
                }
            }
        });
        return fxUI;
    }

    @Override
    public void render(final BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                repaintBoardCanvas(view);
            }
        });
    }

    @Override
    public MoveCommand getCommand() throws IOException {
        return moveCommand;
    }

    private void repaintBoardCanvas(BoardView view) {
        if(step == maxSteps){
            step=0;
            round++;
        }
        step++;

        statusLabel.setText("Step " + step + " von " + maxSteps + " (Round " + round + " von " +  maxRounds + ")");
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.setFill(Color.BLACK);
        XY viewSize = view.getSize();

        Image imageWall = new Image(getClass().getResourceAsStream("..\\Images\\wall.jpg"));
        Image imageBadBeast = new Image(getClass().getResourceAsStream("..\\Images\\badbeast.gif"));
        Image imageGoodBeast = new Image(getClass().getResourceAsStream("..\\Images\\goodbeast.gif"));
        Image imageBadPlant = new Image(getClass().getResourceAsStream("..\\Images\\badplant.gif"));
        Image imageGoodPlant = new Image(getClass().getResourceAsStream("..\\Images\\goodplant.gif"));

        for(int y = 0; y<viewSize.y; y++){
            for(int x = 0; x<viewSize.x; x++) {
                switch(view.getEntityType(x,y)){
                    case WALL:
                        gc.drawImage(imageWall, x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        //gc.setFill(Color.CHOCOLATE);
                        //gc.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case BAD_BEAST:
                        gc.drawImage(imageBadBeast, x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        //gc.setFill(Color.DARKRED);
                        //gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        break;
                    case GOOD_BEAST:
                        gc.drawImage(imageGoodBeast, x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        //gc.setFill(Color.FORESTGREEN);
                        //gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        break;
                    case BAD_PLANT:
                        gc.drawImage(imageBadPlant, x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        //gc.setFill(Color.INDIANRED);
                        //gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        break;
                    case GOOD_PLANT:
                        gc.drawImage(imageGoodPlant, x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        //gc.setFill(Color.LIGHTGREEN);
                        //gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        break;
                    case MASTER_SQUIRREL:
                        gc.setFill(Color.DARKSLATEBLUE);
                        gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        break;
                    case MINI_SQUIRREL:
                        gc.setFill(Color.SKYBLUE);
                        gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                        break;
                }
            }
        }
    }


    @Override
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);
            }
        });
    }
}
