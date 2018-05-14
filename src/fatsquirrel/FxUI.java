package fatsquirrel;

import fatsquirrel.Console.MoveCommand;
import fatsquirrel.core.BoardView;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.core.Entities.Wall;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class FxUI extends Scene implements UI {

    private Canvas boardCanvas;
    private Label msgLabel;
    private static final int CELL_SIZE = 20;
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
        statusLabel.setText("Energie: ");
        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
        fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println("Es wurde folgende Taste gedrückt: " + keyEvent.getCode() + " bitte behandeln!");
                // TODO handle event
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
        return null;
    }

    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        gc.setFill(Color.BLACK);
        XY viewSize = view.getSize();


        for(int y=0; y<viewSize.Y; y++){
            for(int x=0; x<viewSize.X; x++){
                if(view.getEntityType(x,y).equals(EntityType.Wall)){
                    gc.setFill(Color.CHOCOLATE);
                    gc.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.BadBeast)){
                    gc.setFill(Color.RED);
                    gc.fillOval(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.GoodBeast)){
                    gc.setFill(Color.CORNFLOWERBLUE);
                    gc.fillOval(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.GoodPlant)){
                    gc.setFill(Color.GREENYELLOW);
                    gc.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.BadPlant)){
                    gc.setFill(Color.DARKOLIVEGREEN);
                    gc.fillRect(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.HandOperatedMasterSquirrel)){
                    gc.setFill(Color.DARKGOLDENROD);
                    gc.fillOval(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.MasterSquirrelBot)){
                    gc.setFill(Color.YELLOW);
                    gc.fillOval(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                }
                if(view.getEntityType(x,y).equals(EntityType.MiniSquirrel)){
                    gc.setFill(Color.DARKSLATEGRAY);
                    gc.fillOval(x*CELL_SIZE,y*CELL_SIZE,CELL_SIZE,CELL_SIZE);
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
