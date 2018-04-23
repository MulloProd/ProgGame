package fatsquirrel.core.Entities;

import fatsquirrel.XY;

public class GoodBeast extends Entity {

    private XY position;

    public GoodBeast(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
        nextStepCounter =4;
    }

    @Override
    public void nextStep(EntityContext entityContext) {

        //nextStep nur bei jedem 4. Schritt
        if(nextStepCounter == 4) {
            PlayerEntity playerEntity = entityContext.nearestPlayerEntity(position);
            XY playerEntityXY = playerEntity.getPosition();

            //Höhen- und Breitenunterschied
            int xDiff = playerEntityXY.X - this.getPosition().X;
            int yDiff = playerEntityXY.Y - this.getPosition().Y;

            //Vektor bestimmen
            int xVector=0;
            int yVector=0;

            if(xDiff<0)
                xVector=1;
            else if(xDiff>0)
                xVector=-1;

            if(yDiff<0)
                yVector=1;
            else if(yDiff>0)
                yVector=-1;

            //Differenzen summieren
            int sumDiff = xDiff+yDiff;

            //Wenn näher als 6 Steps zum Squirrel, dann lauf weg vom Squirrel, sonst random
            if(sumDiff<=6){
                entityContext.tryMove(this, new XY(xVector, yVector));
            }
            else{
                entityContext.tryMove(this, XY.randomVector());
            }
            nextStepCounter--;
        }
        else if(nextStepCounter == 1){
            nextStepCounter = 4;
        }
        else{
            nextStepCounter--;
        }

    }
}
