package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class FlareGun extends PApplet {

    Main main;

    public PVector pos;


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
            shoot(main.mouseX,main.mouseY);
        }
    }

    public void shoot(float mX,float mY)
    {
        if(main.fired) {
            Flare flare = new Flare(pos.x, pos.y,mX,mY, main);
            main.clip.add(flare);
            //System.out.println(main.clip.size());
            main.fired=false;
            //System.out.println(fired);
        }
    }




}