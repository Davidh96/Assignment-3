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

    public void addGravity(GameObject obj)
    {
        Gravity.pushDown(obj,main);
        inAir=true;
    }

    public abstract void render();
}