package me.itsdavidhunt;

import processing.core.*;


public class FlareGun extends GameObject {

    private float fgWidth= (float)(main.height*.01);

    public FlareGun(Main _main)
    {
        super(_main);
        pos=new PVector(main.player.pos.x,main.player.pos.y);
    }

    public float getWidth()
    {
        return fgWidth;
    }

    //renders Flare Gun
    public void render()
    {
        main.fill(255,0,0);
        main.rect(pos.x,pos.y-fgWidth,fgWidth,-fgWidth);

        if(main.mousePressed)
        {
            shoot();
        }
    }

    //controls the firing of flares
    public void shoot()
    {
        if(main.clip.size()>0) {
            //prevents multiple flares being fired at once
            if (main.fired) {
                main.objects.add(main.clip.get(0));
                main.clip.remove(0);
                main.fired = false;
            }
        }
    }





}