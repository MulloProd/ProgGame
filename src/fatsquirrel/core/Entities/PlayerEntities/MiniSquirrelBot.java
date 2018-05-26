package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.BotControllerFactory;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.Direction;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;

public class MiniSquirrelBot extends MiniSquirrel{
    private final BotController botController;
    private final BotControllerFactory botControllerFactory;
    private XY position;
    private int energy;

    public MiniSquirrelBot(int id, int energy, XY position, MasterSquirrel masterSquirrel) {
        super(id, energy, position, masterSquirrel);
        this.position = position;
        this.energy = energy;

        botControllerFactory = new botControllerFactory();
        botController = botControllerFactory.createMiniBotController();
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        botController.nextStep(new ControllerContextImpl(entityContext));
    }

    private class ControllerContextImpl implements ControllerContext{

        EntityContext entityContext;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext =entityContext;
        }

        @Override
        public XY getViewLowerLeft() {
            return new XY(position.X-10, position.Y+10);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(position.X+10, position.Y-10);
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return entityContext.getEntityType(xy);
        }

        @Override
        public void move(XY direction) {
            entityContext.tryMove(MiniSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return entityContext.getEntityAt(position.X,position.Y).getEnergy();
        }

        @Override
        public void doImplosion(int impactRadius) {
            //entityContext.kill(MiniSquirrelBot.this);
            System.out.println("done");
        }

        @Override
        public Direction getMasterDirection() {
            XY xyMaster = getMasterSquirrel().getPosition();
            int x = position.X - xyMaster.X;
            int y = position.Y - xyMaster.Y;

            //Himmelsrichtung ermitteln
            if(x>=0 && y>=0){
                if(x>=y)
                    return Direction.LEFT;
                else
                    return Direction.UP;
            }
            else if(x>=0 && y<=0){
                if(x>=Math.abs(y))
                    return Direction.LEFT;
                else
                    return Direction.DOWN;
            }
            else if(x<=0 && y>=0){
                if(Math.abs(x)>=y)
                    return Direction.RIGHT;
                else
                    return Direction.UP;
            }
            else if(x<=0 && y<=0){
                if(Math.abs(x)>=Math.abs(y))
                    return Direction.RIGHT;
                else
                    return Direction.DOWN;
            }

            return null;
        }
    }
}
