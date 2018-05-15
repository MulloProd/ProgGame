package fatsquirrel.UIs;

import fatsquirrel.Game.GameCommandType;
import fatsquirrel.Console.MoveCommand;
import fatsquirrel.XY;
import fatsquirrel.core.BoardView;
import fatsquirrel.core.Entities.EntityType;
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
    private static final int CELL_SIZE = 40;
    private static MoveCommand moveCommand;
    private static Label statusLabel;

    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
    }

    public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.X * CELL_SIZE, boardSize.Y * CELL_SIZE);
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
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.setFill(Color.BLACK);
        XY viewSize = view.getSize();

        for(int y=0; y<viewSize.Y; y++){
            for(int x=0; x<viewSize.X; x++) {
                if (view.getEntityType(x, y).equals(EntityType.Wall)) {
                    gc.drawImage(new Image(getClass().getResourceAsStream("..\\Images\\wall.jpg")), x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    //gc.setFill(Color.CHOCOLATE);
                    //gc.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                } else if (view.getEntityType(x, y).equals(EntityType.BadBeast)) {
                    //gc.drawImage(new Image(getClass().getResourceAsStream("..\\Images\\badbeast.gif")), x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                    gc.setFill(Color.DARKGREEN);
                    gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (view.getEntityType(x, y).equals(EntityType.GoodBeast)) {
                    gc.setFill(Color.CORNFLOWERBLUE);
                    gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (view.getEntityType(x, y).equals(EntityType.GoodPlant)) {
                    //gc.drawImage(new Image(getClass().getResourceAsStream("Images\\goodplant.png")), x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                    gc.setFill(Color.SKYBLUE);
                    gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (view.getEntityType(x, y).equals(EntityType.BadPlant)) {
                    //gc.drawImage(new Image(getClass().getResourceAsStream("Images\\badplant.jpg")), x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                    gc.setFill(Color.FORESTGREEN);
                    gc.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                } else if (view.getEntityType(x, y).equals(EntityType.HandOperatedMasterSquirrel)) {
                    gc.setFill(Color.BLACK);
                    gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    statusLabel.setText("Energie: " + view.getEntityAt(x, y).getEnergy());
                } else if (view.getEntityType(x, y).equals(EntityType.MasterSquirrelBot)) {
                    //gc.drawImage(new Image(getClass().getResourceAsStream("Images\\squirrel.png")), x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                    gc.setFill(Color.DARKRED);
                    gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    statusLabel.setText("Energie: " + view.getEntityAt(x, y).getEnergy());
                } else if (view.getEntityType(x, y).equals(EntityType.MiniSquirrel)) {
                    gc.setFill(Color.INDIANRED);
                    gc.fillOval(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
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
