package me.itsdavidhunt;

import processing.core.*;

public class Gravity extends PApplet {

    private static Main main;

    //forces object downwards
    public static void pushDown(GameObject obj, Main _main)
    {
        main=_main;

        //if the object is in the air
        if(obj.inAir) {
            obj.pos.y +=main.height*obj.speed ;

            //the flare drops faster the longer it is in the air
            obj.speed+=(obj.speed*.03);
        }
        //if the object is not in the air
        if(!obj.inAir)
        {
            //reset drop speed
            obj.speed=(float).001;
        }

    }
}