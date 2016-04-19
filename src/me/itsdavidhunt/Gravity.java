package me.itsdavidhunt;

import processing.core.*;

public class Gravity extends PApplet {

    private static Main main;

    private static float initSpeed=(float).001;
    private static float terminalVelocity=(float)0.012;
    private static float incSpeed=(float).03;

    //forces object downwards
    public static void pushDown(GameObject obj, Main _main)
    {
        main=_main;

        //if the object is in the air
        if(obj.inAir) {
            obj.pos.y +=main.height*obj.speed ;

            if(obj.speed<terminalVelocity) {
                //the object drops faster the longer it is in the air
                obj.speed += (obj.speed * incSpeed);
            }
        }
        //if the object is not in the air
        if(!obj.inAir)
        {
            //reset drop speed
            obj.speed=initSpeed;
        }

    }
}