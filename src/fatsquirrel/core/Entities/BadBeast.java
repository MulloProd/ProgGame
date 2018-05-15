package fatsquirrel.core.Entities;

import fatsquirrel.XY;
import fatsquirrel.core.Entities.PlayerEntities.PlayerEntity;

public class BadBeast extends Entity {

    private XY position;
    private int biteCounter = 7;

    public BadBeast(int id, int energy, XY position) {
        super(id, energy, position);
        this.position = position;
        setNextStepCounter(4);
    }

    @Override
    public void nextStep(EntityContext entityContext) {

        //nextStep nur bei jedem 4. Schritt
        if(getNextStepCounter() == 4) {
            PlayerEntity playerEntity = entityContext.nearestPlayerEntity(position);
            XY playerEntityXY = playerEntity.getPosition();

            //Höhen- und Breitenunterschied
            int xDiff = playerEntityXY.X - this.getPosition().X;
            int yDiff = playerEntityXY.Y - this.getPosition().Y;

            //Vektor bestimmen
            int xVector=0;
            int yVector=0;

            if(xDiff<0)
                xVector=-1;
            else if(xDiff>0)
                xVector=1;

            if(yDiff<0)
                yVector=-1;
            else if(yDiff>0)
                yVector=1;

            //Differenzen summieren
            int sumDiff = xDiff+yDiff;

            //Wenn näher als 6 Steps zum Squirrel, dann lauf zum Squirrel, sonst random
            if(sumDiff<=6){
                entityContext.tryMove(this, new XY(xVector, yVector));
            }
            else{
                entityContext.tryMove(this, XY.randomVector());
            }
            setNextStepCounter(-1);
        }
        else if(getNextStepCounter() == 1){
            setNextStepCounter(3);
        }
        else{
            setNextStepCounter(-1);
        }
    }

    public int getBiteCounter() {
        return biteCounter;
    }

    public void decreaseBiteCounter() {
        this.biteCounter --;
    }
}
