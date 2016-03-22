package me.itsdavidhunt;

import processing.core.*;
//gives all Game Objects basic attributes
public abstract class GameObject extends PApplet {

    Main main;
    PVector pos;

    public GameObject(Main _main)
    {
        main = _main;
    }

    public abstract void render();
}