package me.itsdavidhunt;

import processing.core.*;

//gives all Game Objects basic attributes
public abstract class GameObject extends PApplet {

    Main main;
    PVector pos;
    boolean inAir=false;
    float speed=(float).001;

    public GameObject(Main _main)
    {
        main = _main;
    }

    //causes object to be affected by gravity
    public void addGravity(GameObject obj)
    {
        Gravity.pushDown(obj,main);
        //ensures that all objects are affected by gravity
        inAir=true;
    }

    public abstract void render();
    public abstract float getWidth();
}