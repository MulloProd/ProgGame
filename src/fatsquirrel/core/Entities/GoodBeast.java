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

            int x = xNegativ?1:-1;
            int y = yNegativ?1:-1;

            //Wenn näher als 6 Steps zum Squirrel, dann lauf weg, sonst random
            if(sumDiff<=6){
                    entityContext.tryMove(this, new XY(x,y));
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
