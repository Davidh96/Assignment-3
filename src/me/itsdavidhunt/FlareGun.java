package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class FlareGun extends PApplet {

    Main main;

    public PVector pos;
    ArrayList<Flare> clip=new ArrayList<Flare>();

    public FlareGun(float x,float y,Main _main)
    {
        pos=new PVector(x,y);
        main = _main;
    }

    public void render()
    {
        main.fill(255,0,0);
        main.rect(pos.x,pos.y,10,10);
        //System.out.println(clip.size());

        if(main.mousePressed)
        {
            shoot();
        }
    }

    public void shoot()
    {
        Flare flare=new Flare(pos.x,pos.y,main);
        clip.add(flare);
        System.out.println(clip.size());
    }




}