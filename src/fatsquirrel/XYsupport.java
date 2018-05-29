package fatsquirrel;

import java.util.Random;

public class XYsupport {
    public static XY randomVector(){
        XY temp = new XY(0,0);
        while(temp.x==0 && temp.y ==0){
            temp = new XY(new Random().nextInt(3)-1, new Random().nextInt(3)-1);
        }
        return temp;
    }
}
