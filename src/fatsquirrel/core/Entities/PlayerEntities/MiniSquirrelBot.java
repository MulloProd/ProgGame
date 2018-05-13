package fatsquirrel.core.Entities.PlayerEntities;

import fatsquirrel.XY;
import fatsquirrel.botapi.BotController;
import fatsquirrel.botapi.BotControllerFactory;
import fatsquirrel.botapi.ControllerContext;
import fatsquirrel.botapi.Implementation.botControllerFactory;
import fatsquirrel.core.Entities.Entity;
import fatsquirrel.core.Entities.EntityContext;
import fatsquirrel.core.Entities.EntityType;

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
            return null;
        }

        @Override
        public XY getViewUpperRight() {
            return null;
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
            return getEnergy();
        }
    }
}
