package me.itsdavidhunt;

import processing.core.*;

public class Gravity extends PApplet {

    private static Main main;
    private static float speed=(float).001;

    public static void pushDown(GameObject obj, Main _main)
    {
        main=_main;

        if(obj.inAir) {
            obj.pos.y +=main.height*speed ;

            //the flare drops faster the longer it is in the air
            speed+=(speed*.01);
        }
    }
}