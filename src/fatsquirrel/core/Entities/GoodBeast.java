package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class GoodBeast extends Entity {

    private XY position;
    private int nextStepCount = 4;

    public GoodBeast(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
    }

    @Override
    public void nextStep(EntityContext entityContext) {

        if(nextStepCount == 4) {
            //setPosition(getPosition().add(XY.randomVector()));
            PlayerEntity playerEntity = entityContext.nearestPlayerEntity(position);
            XY playerEntityXY = playerEntity.getPosition();

            //Höhen- und Breitenunterschied
            int xDiff = playerEntityXY.X - this.getPosition().X;
            int yDiff = playerEntityXY.Y - this.getPosition().Y;

            //Wichtig fürs Weglaufen
            boolean xNegativ = false;
            boolean yNegativ = false;

            //Differenzen positiv darstellen
            if (xDiff < 0) {
                xDiff = Math.abs(xDiff);
                xNegativ = true;
            }
            if (yDiff < 0){
                yDiff = Math.abs(yDiff);
                yNegativ = true;
            }

            //Differenzen summieren
            int sumDiff = xDiff+yDiff;

            //Wenn näher als 6 Steps zum Squirrel, dann lauf weg, sonst random
            if(sumDiff<=6) {
                if(xNegativ && yNegativ){
                    entityContext.tryMove(this, new XY(1,1));
                }
                else if(xNegativ && yNegativ == false){
                    entityContext.tryMove(this, new XY(1,-1));
                }
                else if(xNegativ == false && yNegativ){
                    entityContext.tryMove(this, new XY(-1,1));
                }
                else if(xNegativ == false && yNegativ == false){
                    entityContext.tryMove(this, new XY(-1,-1));
                }

            }
            else{
                entityContext.tryMove(this, XY.randomVector());
            }
            nextStepCount--;
        }
        else if(nextStepCount == 1){
            nextStepCount = 4;
        }
        else{
            nextStepCount--;
        }

    }
}
