package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Boss {

    Main main;
    float sectorW;
    float randHeight;
    int headNum;

    public Boss(Main _main)
    {
        main=_main;
        sectorW=main.width/3;
        randHeight=main.height/2;
    }

    public void render()
    {
        main.fill(255,0,0);

        main.ellipse((sectorW*headNum)-sectorW/2,randHeight,20,20);

    }

    public void movement()
    {

    }
}
