package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.*;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.BoardConfig;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.Logging;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class MasterSquirrelBot extends MasterSquirrel {
    private final BotControllerFactory botControllerFactory;
    private BotController botController;
    private final Logging logging;

    public MasterSquirrelBot(int id, int energy, XY position) {
        super(id, energy, position);

        botControllerFactory = new botControllerFactory();
        botController = botControllerFactory.createMasterBotController();

        logging = new Logging("MasterBot#" + id, "MasterBots");
    }

    @Override
    public void nextStep(EntityContext entityContext) throws IOException {

        if(getCollisionCounter()==0){
            EventLogger handler = new EventLogger(new ControllerContextImpl(entityContext), logging);
            ControllerContext proxy = (ControllerContext) Proxy.newProxyInstance(
                    ControllerContext.class.getClassLoader(),
                    new Class[] { ControllerContext.class },
                    handler);

            botController.nextStep(proxy);
        }
        else{
            setCollisionCounter(-1);
        }


    }

    private class ControllerContextImpl implements ControllerContext{

        EntityContext entityContext;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext =entityContext;
        }

        @Override
        public XY getViewLowerLeft() {
            int x = MasterSquirrelBot.this.getPosition().x-15;
            int y = MasterSquirrelBot.this.getPosition().y +15;
            if(x<0)
                x=0;
            if(y>entityContext.getSize().y)
                y=entityContext.getSize().y-1;
            return new XY(x, y);
        }

        @Override
        public XY getViewUpperRight() {
            int x = MasterSquirrelBot.this.getPosition().x+15;
            int y = MasterSquirrelBot.this.getPosition().y -15;
            if(x>entityContext.getSize().x)
                x=entityContext.getSize().x-1;
            if(y<0)
                y=0;
            return new XY(x, y);
        }

        @Override
        public XY locate() {
            return MasterSquirrelBot.this.getPosition();
        }

        @Override
        public EntityType getEntityAt(XY xy) throws OutOfViewException {
            if(xy.x < getViewLowerLeft().x || xy.x > getViewUpperRight().x ||
                    xy.y< getViewUpperRight().y || xy.y > getViewLowerLeft().y)
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

            if(xy.equals(EntityType.MINI_SQUIRREL)){
                if(this.equals(((MiniSquirrelBot)entityContext.getEntityAt(xy.x,xy.y)).getMasterSquirrel()))
                    return true;
                else
                    return false;
            }
            else
                return false;
        }

        @Override
        public void move(XY direction) {
            entityContext.tryMove(MasterSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) throws SpawnException {

            if(MasterSquirrelBot.this.getEnergy()<100 || energy>=MasterSquirrelBot.this.getEnergy())
                throw new SpawnException();

            try {
                EntityType entityType = (getEntityAt(new XY(MasterSquirrelBot.this.getPosition().x+direction.x, MasterSquirrelBot.this.getPosition().y+direction.y)));
                if(entityType !=EntityType.NONE)
                    throw new SpawnException();
            } catch (OutOfViewException e) {
                throw new SpawnException();
            }

            entityContext.spawn_Mini(direction, energy, MasterSquirrelBot.this);
        }

        @Override
        public void implode(int impactRadius) {

        }

        @Override
        public int getEnergy() {
            return MasterSquirrelBot.this.getEnergy();
        }

        @Override
        public XY directionOfMaster() {
            return null;
        }

        @Override
        public long getRemainingSteps() {
            return BoardConfig.getSteps()-BoardConfig.getCurrentSteps();
        }
    }
}
