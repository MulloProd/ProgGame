package fatsquirrel.core;

import fatsquirrel.Logging;
import fatsquirrel.XY;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Entities.PlayerEntities.MasterSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.MiniSquirrel;
import fatsquirrel.core.Entities.PlayerEntities.PlayerEntity;

import java.util.Random;

public class FlattenedBoard implements EntityContext, BoardView {

    private final Board board;
    private final int width;
    private final int height;

    private static Logging logging = new Logging(FlattenedBoard.class.getSimpleName());

    public FlattenedBoard(int width, int height, Board board){
        this.width = width;
        this.height = height;
        this.board = board;
    }

    @Override
    public XY getSize() {
        return new XY(width, height);
    }

    @Override
    public void tryMove(MiniSquirrel miniSquirrel, XY moveDirection) {

        if(moveDirection==null)
            return;
        if(moveDirection.x == 0 && moveDirection.y == 0 && moveDirection.x <=1 && moveDirection.y<=1)
            return;

        int x = miniSquirrel.getPosition().x +moveDirection.x;
        int y = miniSquirrel.getPosition().y +moveDirection.y;

        //Noch Energy vorhanden
        if (miniSquirrel.getEnergy() >= 1) {
            if(board.getEntity(x,y) == null) {
                board.moveEntity(miniSquirrel, miniSquirrel.getPosition().plus(moveDirection));
                miniSquirrel.updateEnergy(-1);
            }
            else if(board.getEntity(x,y) instanceof MasterSquirrel){
                //Kollision mit eigenem MASTER_SQUIRREL
                if(board.getEntity(x,y) == miniSquirrel.getMasterSquirrel()){
                    miniSquirrel.updateEnergy(-1);
                    board.getEntity(x,y).updateEnergy(miniSquirrel.getEnergy());
                    kill(miniSquirrel);
                }
                else {
                    kill(miniSquirrel);
                }
            }
            else if(board.getEntity(x,y) instanceof MiniSquirrel){
                MiniSquirrel miniSquirrelTemp = (MiniSquirrel)board.getEntity(x,y);
                //Kollision mit fremden MiniSquirrel
                if(miniSquirrel.getMasterSquirrel() != miniSquirrelTemp.getMasterSquirrel()){
                    miniSquirrel.updateEnergy(0);
                    board.getEntity(x,y).updateEnergy(0);
                    kill(miniSquirrel);

                    //ggf. auch anderes MiniSquirrel töten
                    miniSquirrelTemp.updateEnergy(-miniSquirrel.getEnergy());
                    if(miniSquirrelTemp.getEnergy()<=0)
                        kill(miniSquirrelTemp);
                }
            }
            else if(board.getEntity(x,y) instanceof GoodPlant
                    || board.getEntity(x,y) instanceof BadPlant){
                miniSquirrel.updateEnergy(board.getEntity(x,y).getEnergy());
                killAndReplace(board.getEntity(x,y));

                board.moveEntity(miniSquirrel, miniSquirrel.getPosition().plus(moveDirection));
            }
            else if(board.getEntity(x,y) instanceof GoodBeast){
                miniSquirrel.updateEnergy(board.getEntity(x,y).getEnergy());
                killAndReplace(board.getEntity(x,y));

                if(miniSquirrel.getEnergy()<=0)
                    kill(miniSquirrel);
                else
                    board.moveEntity(miniSquirrel, miniSquirrel.getPosition().plus(moveDirection));
            }
            else if(board.getEntity(x,y) instanceof BadBeast){
                BadBeast badBeast = (BadBeast)board.getEntity(x,y);
                if(badBeast.getBiteCounter()>1){
                    miniSquirrel.updateEnergy(badBeast.getEnergy());
                    badBeast.decreaseBiteCounter();
                }
                else if(badBeast.getBiteCounter()==1){
                    miniSquirrel.updateEnergy(badBeast.getEnergy());
                    badBeast.decreaseBiteCounter(); //Reset BiteCounter
                    killAndReplace(badBeast);
                }
            }
            else if(board.getEntity(x,y) instanceof Wall){
                miniSquirrel.updateEnergy(-10);

                if(miniSquirrel.getEnergy()<=0)
                    kill(miniSquirrel);
            }
        }
        else if (miniSquirrel.getEnergy() <= 0) {
            kill(miniSquirrel);
        }
    }

    @Override
    public void tryMove(GoodBeast goodBeast, XY moveDirection) {

        if(moveDirection==null)
            return;
        if(moveDirection.x == 0 && moveDirection.y == 0 && moveDirection.x <=1 && moveDirection.y<=1)
            return;

        int x = goodBeast.getPosition().x +moveDirection.x;
        int y = goodBeast.getPosition().y +moveDirection.y;

        if(board.getEntity(x,y) == null){
            board.moveEntity(goodBeast, goodBeast.getPosition().plus(moveDirection));
        }
        else if(board.getEntity(x,y) instanceof PlayerEntity){
            board.getEntity(x,y).updateEnergy(goodBeast.getEnergy());
            killAndReplace(goodBeast);
        }

    }

    @Override
    public void tryMove(BadBeast badBeast, XY moveDirection) {

        if(moveDirection==null)
            return;
        if(moveDirection.x == 0 && moveDirection.y == 0 && moveDirection.x <=1 && moveDirection.y<=1)
            return;

        int x = badBeast.getPosition().x +moveDirection.x;
        int y = badBeast.getPosition().y +moveDirection.y;

        if(board.getEntity(x,y) == null){
            board.moveEntity(badBeast, badBeast.getPosition().plus(moveDirection));
        }
        else if(board.getEntity(x,y) instanceof PlayerEntity){
            if(badBeast.getBiteCounter()>1){
                board.getEntity(x,y).updateEnergy(badBeast.getEnergy());
                //if(board.getEntity(x,y).getEnergy()<=0){
                //    kill(board.getEntity(x,y));
                //}
                badBeast.decreaseBiteCounter();
            }
            else if(badBeast.getBiteCounter()==1){
                board.getEntity(x,y).updateEnergy(badBeast.getEnergy());
                //if(board.getEntity(x,y).getEnergy()<=0){
                //    kill(board.getEntity(x,y));
                //}
                badBeast.decreaseBiteCounter(); //Reset BiteCounter
                killAndReplace(badBeast);
            }
        }
    }

    @Override
    public void tryMove(MasterSquirrel masterSquirrel, XY moveDirection) {

        if(moveDirection==null)
            return;
        if(moveDirection.x == 0 && moveDirection.y == 0 && moveDirection.x <=1 && moveDirection.y<=1)
            return;

        //aktuelle Position holen
        int x = masterSquirrel.getPosition().x +moveDirection.x;
        int y = masterSquirrel.getPosition().y +moveDirection.y;

        //Kollisionsabfrage
        if(board.getEntity(x,y) == null){
            board.moveEntity(masterSquirrel, masterSquirrel.getPosition().plus(moveDirection));
        }
        else if(board.getEntity(x,y) instanceof Wall){
            masterSquirrel.setCollisionCounter(3);
            masterSquirrel.updateEnergy(-10);
            return;
        }
        else if(board.getEntity(x,y) instanceof GoodPlant
                || board.getEntity(x,y) instanceof BadPlant){
            masterSquirrel.updateEnergy(board.getEntity(x,y).getEnergy());
            killAndReplace(board.getEntity(x,y));

            board.moveEntity(masterSquirrel, masterSquirrel.getPosition().plus(moveDirection));
        }
        else if(board.getEntity(x,y) instanceof GoodBeast){
            masterSquirrel.updateEnergy(board.getEntity(x,y).getEnergy());
            killAndReplace(board.getEntity(x,y));

            board.moveEntity(masterSquirrel, masterSquirrel.getPosition().plus(moveDirection));
        }
        else if(board.getEntity(x,y) instanceof BadBeast){
            BadBeast badBeast=(BadBeast)board.getEntity(x,y);
            masterSquirrel.updateEnergy(badBeast.getEnergy());
            if(badBeast.getBiteCounter()>1){
                badBeast.decreaseBiteCounter();
            }
            else if((badBeast.getBiteCounter()==1)){
                badBeast.decreaseBiteCounter(); //Reset BiteCounter
                killAndReplace(badBeast);
            }
        }
        else if(board.getEntity(x,y) instanceof MiniSquirrel){
            MasterSquirrel masterSquirrelTemp = ((MiniSquirrel)board.getEntity(x,y)).getMasterSquirrel();
            if(masterSquirrelTemp == masterSquirrel){
                masterSquirrel.updateEnergy(board.getEntity(x,y) .getEnergy());
                kill(board.getEntity(x,y));
            }
            else{
                masterSquirrel.updateEnergy(board.getEntity(x,y).getEnergy());
                kill(board.getEntity(x,y));
            }

            board.moveEntity(masterSquirrel, masterSquirrel.getPosition().plus(moveDirection));
        }


    }

    @Override
    public PlayerEntity nearestPlayerEntity(XY pos) {

        PlayerEntity nearestPlayerEntity = null;
        int xDiff = width;
        int yDiff = height;

        for(int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(board.getEntity(i,j) instanceof PlayerEntity){
                    if(Math.abs(pos.x -i)+Math.abs(pos.y -j)<xDiff+yDiff){
                        xDiff = Math.abs(pos.x -i);
                        yDiff = Math.abs(pos.y -j);
                        nearestPlayerEntity = (PlayerEntity)board.getEntity(i,j);
                    }
                }
            }
        }

        return nearestPlayerEntity;
    }

    @Override
    public void kill(Entity entity) {
        if(entity instanceof MasterSquirrel){

        }
        else{
            entity.getLogging().getLogger().info("Entity killed!");
            board.removeEntity(entity);
        }
    }

    @Override
    public void killAndReplace(Entity entity) {
        boolean success = false;
        while(!success) {
            int x = new Random().nextInt(width - 1);
            int y = new Random().nextInt(height - 1);
            EntityType entityType = getEntityType(entity.getPosition());
            logging.getLogger().finer("killAndReplace; Type: "+entityType+"; Class: "+entity.getClass().getSimpleName());
            if(entityType==EntityType.NONE)
                logging.getLogger().severe("Can´t get Type!");
            if(board.setNewEntity(x,y, entityType))
                success = true;
        }
        kill(entity);
    }

    @Override
    public EntityType getEntityType(XY xy) {
        EntityType type = EntityType.NONE;
        if(board.getEntity(xy.x, xy.y)!=null) {
            switch (board.getEntity(xy.x, xy.y).getClass().getSimpleName()) {
                case "Wall":
                    type = EntityType.WALL;
                    break;
                case "BadPlant":
                    type = EntityType.BAD_PLANT;
                    break;
                case "GoodPlant":
                    type = EntityType.GOOD_PLANT;
                    break;
                case "BadBeast":
                    type = EntityType.BAD_BEAST;
                    break;
                case "GoodBeast":
                    type = EntityType.GOOD_BEAST;
                    break;
            }
            if (board.getEntity(xy.x, xy.y) instanceof MasterSquirrel)
                type = EntityType.MASTER_SQUIRREL;
            else if (board.getEntity(xy.x, xy.y) instanceof MiniSquirrel)
                type = EntityType.MINI_SQUIRREL;

        }
        return type;
    }
    @Override
    public EntityType getEntityType(int x, int y) {
        return getEntityType(new XY(x,y));
    }

    @Override
    public void spawn_Mini(XY direction, int energy, MasterSquirrel masterSquirrel){
        int x = masterSquirrel.getPosition().x +direction.x;
        int y=masterSquirrel.getPosition().y +direction.y;
        board.setNewMiniSquirrel(x, y, energy, masterSquirrel);
    }

    public String allEntitiesToString(){
        return board.allEntitiesToString();
    }

    @Override
    public Entity getEntityAt(int x, int y){
        return board.getEntity(x,y);
    }
}
