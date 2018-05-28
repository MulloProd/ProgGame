package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.*;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;
import fatsquirrel.Logging;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class MasterSquirrelBot extends MasterSquirrel {
    private final BotControllerFactory botControllerFactory;
    private final BotController botController;
    private XY position;
    private int energy;
    private final Logging logging;

    public MasterSquirrelBot(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position; //über Getter regeln?
        this.energy = energy;

        botControllerFactory = new botControllerFactory();
        botController = botControllerFactory.createMasterBotController();

        logging = new Logging("MasterBot#" + id, "MasterBots");
    }

    @Override
    public void nextStep(EntityContext entityContext) throws IOException {
        EventLogger handler = new EventLogger(new ControllerContextImpl(entityContext), logging);
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
            return new XY(position.x -15, position.y +15);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY (position.x +15, position.y -15);
        }

        @Override
        public XY locate() {
            return position;
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

            if(energy<100 || energy>=getEnergy())
                throw new SpawnException();

            try {
                if((getEntityAt(new XY(getPosition().x+direction.x, getPosition().y+direction.y))) !=EntityType.NONE)
                    throw new SpawnException();
            } catch (OutOfViewException e) {
                e.printStackTrace();
            }

            entityContext.spawn_Mini(direction, energy, MasterSquirrelBot.this);
        }

        @Override
        public void implode(int impactRadius) {

        }

        @Override
        public int getEnergy() {
            return energy;
        }

        @Override
        public XY directionOfMaster() {
            return null;
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }
    }
}
