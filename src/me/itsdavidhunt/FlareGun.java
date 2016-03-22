package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class FlareGun extends GameObject {


    public FlareGun(Main _main)
    {
        super(_main);
        pos=new PVector(main.player.pos.x,main.player.pos.y);

    }

    public void render()
    {
        super.main.fill(255,0,0);
        super.main.rect(pos.x,pos.y,10,10);
        //System.out.println(clip.size());

        if(super.main.mousePressed)
        {
            shoot(super.main.mouseX,super.main.mouseY);
        }

        addGravity(this);
    }

    public void shoot(float mX,float mY)
    {
        if(super.main.fired) {
            Flare flare = new Flare(main);
            super.main.clip.add(flare);
            //System.out.println(main.clip.size());
            super.main.fired=false;
            //System.out.println(fired);
        }
    }




}