package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.*;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.Entities.*;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.Logging;

import java.lang.reflect.Proxy;

public class MiniSquirrelBot extends MiniSquirrel{
    private final BotController botController;
    private final BotControllerFactory botControllerFactory;
    private final Logging logging;

    public MiniSquirrelBot(int id, int energy, XY position, MasterSquirrel masterSquirrel) {
        super(id, energy, position, masterSquirrel);

        botControllerFactory = new botControllerFactory();
        botController = botControllerFactory.createMiniBotController();

        logging = new Logging("MiniBot#" + id, "MiniBots");
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        EventLogger handler = new EventLogger(new ControllerContextImpl(entityContext) {
        }, logging);
        ControllerContext proxy = (ControllerContext) Proxy.newProxyInstance(
                ControllerContext.class.getClassLoader(),
                new Class[] { ControllerContext.class },
                handler);

        botController.nextStep(proxy);
    }

    private class ControllerContextImpl implements ControllerContext{

        EntityContext entityContext;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext =entityContext;
        }

        @Override
        public XY getViewLowerLeft() {
            return new XY(MiniSquirrelBot.this.getPosition().x-10, MiniSquirrelBot.this.getPosition().y+10);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(MiniSquirrelBot.this.getPosition().x+10, MiniSquirrelBot.this.getPosition().y-10);
        }

        @Override
        public XY locate() {
            return MiniSquirrelBot.this.getPosition();
        }

        @Override
        public EntityType getEntityAt(XY xy) throws OutOfViewException {
            if(xy.x < getViewLowerLeft().x || xy.x > getViewUpperRight().x ||
                    xy.y < getViewUpperRight().y || xy.y > getViewLowerLeft().y)
                throw new OutOfViewException();
            else
                return entityContext.getEntityType(xy);
        }

        @Override
        public boolean isMine(XY xy) {
            try {
                getEntityAt(xy);
            } catch (OutOfViewException e) {
                e.printStackTrace();
            }

            if(entityContext.getEntityAt(xy.x,xy.y).equals(EntityType.MASTER_SQUIRREL)){
                if(MiniSquirrelBot.this.getMasterSquirrel().equals((entityContext.getEntityAt(xy.x,xy.y))))
                    return true;
                else
                    return false;
            }
            else
                return false;
        }

        @Override
        public void move(XY direction) {
            if(!(direction.equals(XY.ZERO_ZERO)))
                entityContext.tryMove(MiniSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public void implode(int impactRadius) {
            int xLeft = MiniSquirrelBot.this.getPosition().x -impactRadius;
            int yLeft = MiniSquirrelBot.this.getPosition().y -impactRadius;
            int xRight = MiniSquirrelBot.this.getPosition().x +impactRadius;
            int yRight = MiniSquirrelBot.this.getPosition().y +impactRadius;

            if(xLeft <=0)
                xLeft=1;
            if(yLeft<=0)
                yLeft=1;
            if(xRight>=entityContext.getSize().x)
                xRight=entityContext.getSize().x -1;
            if(yRight>=entityContext.getSize().y)
                yRight=entityContext.getSize().y -1;

            double impactArea = impactRadius*impactRadius*Math.PI;

            for(int y=yLeft; y<=yRight; y++){
                for(int x=xLeft; x<=xRight; x++){
                    Entity entity = entityContext.getEntityAt(x,y);
                    if(entity != null && !(entity.equals(MiniSquirrelBot.this)) && !(entity instanceof Wall)) {
                        double distance = MiniSquirrelBot.this.getPosition().distanceFrom(entity.getPosition());
                        int energyLoss = (int) (200 * (MiniSquirrelBot.this.getEnergy()/impactArea) * (1 - distance/impactRadius));

                        if(entity.getEnergy()<energyLoss)
                            energyLoss=Math.abs(entity.getEnergy());

                        //Abfrage der einzelnen Entities
                        if (entity instanceof GoodPlant || entity instanceof GoodBeast) {
                            entity.updateEnergy(-energyLoss);
                            MiniSquirrelBot.this.getMasterSquirrel().updateEnergy(energyLoss);
                        } else if (entity instanceof BadPlant || entity instanceof BadBeast) {
                            entity.updateEnergy(energyLoss);
                        } else if (entity instanceof MiniSquirrel){
                            if(!(((MiniSquirrel) entity).getMasterSquirrel().equals(MiniSquirrelBot.this.getMasterSquirrel()))) {
                                entity.updateEnergy(-energyLoss);
                                MiniSquirrelBot.this.getMasterSquirrel().updateEnergy(energyLoss);
                            }
                        } else if(entity instanceof MasterSquirrel){
                            if(!(entity.equals(MiniSquirrelBot.this.getMasterSquirrel()))){
                                entity.updateEnergy(-energyLoss);
                                MiniSquirrelBot.this.getMasterSquirrel().updateEnergy(energyLoss);
                            }
                        }

                        //ggf. Entity killen
                        if((entity.getEnergy()<=0) && !(entity instanceof PlayerEntity))
                            entityContext.killAndReplace(entity);
                        else if((entity.getEnergy()<=0) && (entity instanceof MiniSquirrel))
                            entityContext.kill(entity);
                    }
                }

            }

            entityContext.kill(MiniSquirrelBot.this);
        }

        @Override
        public int getEnergy() {
            return MiniSquirrelBot.this.getEnergy();
        }

        @Override
        public XY directionOfMaster() {
            XY xyMaster = getMasterSquirrel().getPosition();
            int x = getPosition().x - xyMaster.x;
            int y = getPosition().y - xyMaster.y;

            return new XY(x,y);

            /*
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
            */
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }

    }
}
