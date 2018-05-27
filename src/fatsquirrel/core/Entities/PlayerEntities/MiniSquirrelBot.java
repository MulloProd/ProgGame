package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.BotControllerFactory;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.Direction;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.Entities.*;

public class MiniSquirrelBot extends MiniSquirrel{
    private final BotController botController;
    private final BotControllerFactory botControllerFactory;

    public MiniSquirrelBot(int id, int energy, XY position, MasterSquirrel masterSquirrel) {
        super(id, energy, position, masterSquirrel);

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
            return new XY(MiniSquirrelBot.this.getPosition().X-10, MiniSquirrelBot.this.getPosition().Y+10);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(MiniSquirrelBot.this.getPosition().X+10, MiniSquirrelBot.this.getPosition().Y-10);
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
            return MiniSquirrelBot.this.getEnergy();
        }

        @Override
        public void doImplosion(int impactRadius) {
            int xLeft = MiniSquirrelBot.this.getPosition().X-impactRadius;
            int yLeft = MiniSquirrelBot.this.getPosition().Y-impactRadius;
            int xRight = MiniSquirrelBot.this.getPosition().X+impactRadius;
            int yRight = MiniSquirrelBot.this.getPosition().Y+impactRadius;

            if(xLeft <=0)
                xLeft=1;
            if(yLeft<=0)
                yLeft=1;
            if(xRight>=entityContext.getSize().X)
                xRight=entityContext.getSize().X-1;
            if(yRight>=entityContext.getSize().Y)
                yRight=entityContext.getSize().Y-1;

            double impactArea = impactRadius*impactRadius*Math.PI;

            for(int y=yLeft; y<=yRight; y++){
                for(int x=xLeft; x<=xRight; x++){
                    Entity entity = entityContext.getEntityAt(x,y);
                    if(entity != null && !(entity.equals(MiniSquirrelBot.this)) && !(entity instanceof Wall)) {
                        int distance = Math.abs(MiniSquirrelBot.this.getPosition().X - entity.getPosition().X) +
                            Math.abs(MiniSquirrelBot.this.getPosition().Y - entity.getPosition().Y);
                        int energyLoss = 200 * (MiniSquirrelBot.this.getEnergy()/(int)impactArea) * (1 - distance/impactRadius);

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
            System.out.println("imploded");
        }

        @Override
        public Direction getMasterDirection() {
            XY xyMaster = getMasterSquirrel().getPosition();
            int x = MiniSquirrelBot.this.getPosition().X - xyMaster.X;
            int y = MiniSquirrelBot.this.getPosition().Y - xyMaster.Y;

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
