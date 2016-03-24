package me.itsdavidhunt;

import processing.core.*;

public class Gravity extends PApplet {

    private static Main main;

    public static void pushDown(GameObject obj, Main _main)
    {
        main=_main;

        if(obj.inAir) {
            obj.pos.y +=main.height*obj.speed ;

            //the flare drops faster the longer it is in the air
            obj.speed+=(obj.speed*.01);
        }
        if(!obj.inAir)
        {
            obj.speed=(float).001;
        }

    }
}