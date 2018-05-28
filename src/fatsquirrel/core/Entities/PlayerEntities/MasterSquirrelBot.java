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
        this.position = position;
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
        public EntityType getEntityAt(XY xy) {
            return entityContext.getEntityType(xy);
        }

        @Override
        public void move(XY direction) {
            entityContext.tryMove(MasterSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            entityContext.spawn_Mini(direction, energy, MasterSquirrelBot.this);
        }

        @Override
        public int getEnergy() {
            return energy;
        }

        @Override
        public void doImplosion(int impactRadius) {

        }

        @Override
        public Direction getMasterDirection() {
            return null;
        }
    }
}
