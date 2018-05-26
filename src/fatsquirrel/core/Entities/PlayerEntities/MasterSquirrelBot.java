package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.BotControllerFactory;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.Direction;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;

import java.io.IOException;

public class MasterSquirrelBot extends MasterSquirrel {
    private final BotControllerFactory botControllerFactory;
    private final BotController botController;
    private XY position;
    private int energy;

    public MasterSquirrelBot(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
        this.energy = energy;

        botControllerFactory = new botControllerFactory();
        botController = botControllerFactory.createMasterBotController();
    }

    @Override
    public void nextStep(EntityContext entityContext) throws IOException {
        botController.nextStep(new ControllerContextImpl(entityContext));
    }

    private class ControllerContextImpl implements ControllerContext{

        EntityContext entityContext;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext =entityContext;
        }

        @Override
        public XY getViewLowerLeft() {
            return new XY(position.X-15, position.Y+15);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY (position.X+15, position.Y-15);
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
