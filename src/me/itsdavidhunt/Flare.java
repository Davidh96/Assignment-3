package me.itsdavidhunt;

import processing.core.*;

public class Flare extends PApplet{

    Main main;

    PVector pos;

    public Flare(float x,float y,Main _main)
    {
        main=_main;
        pos=new PVector(x,y);
    }

    public void move()
    {
        pos.x++;
    }
}
