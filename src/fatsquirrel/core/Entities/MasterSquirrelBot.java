package fatsquirrel.core.Entities;

import fatsquirrel.XY;
import fatsquirrel.botapi.ControllerContext;

public class MasterSquirrelBot {
    private class ControllerContextImpl implements ControllerContext{

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
            return null;
        }

        @Override
        public void move(XY direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return 0;
        }
    }
}
