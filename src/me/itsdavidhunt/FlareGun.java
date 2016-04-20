package me.itsdavidhunt;

import processing.core.*;

//holds the flares
public class FlareGun extends GameObject {

    private float fgWidth= (float)(main.height*.03);
    public PImage img;
    public float switchSide=0;

    public FlareGun(Main _main)
    {
        super(_main);
        img=main.loadImage("GunRight.png");
    }

    //renders Flare Gun
    public void render()
    {
        main.fill(255,0,0);
        pos=new PVector(main.player.pos.x+main.player.getWidth()/2+switchSide,main.player.pos.y+main.player.getWidth()/2);
        main.image(img,pos.x,pos.y-fgWidth,fgWidth,-fgWidth);

        if(main.mousePressed)
        {
            shoot();
        }
    }

    //controls the firing of flares
    public void shoot()
    {
        if(main.clip.size()>0 && main.timer>60) {
            //prevents multiple flares being fired at once
            if (main.fired) {
                main.flareSound.play();
                main.flareSound.rewind();
                main.objects.add(main.clip.get(0));
                //spawn at player position
                main.clip.get(0).pos= new PVector(main.gun.pos.x,main.player.pos.y-main.player.getHeight());
                main.clip.get(0).pDest= new PVector(main.gun.pos.x,main.player.pos.y-main.player.getHeight());

                //goToPos holds the positon that the user selected
                main.clip.get(0).goToPos=new PVector(main.mouseX,main.mouseY);
                //will alter how far a flare goes
                main.clip.get(0).speed=(float)(.00001*(main.clip.get(0).getDistance()));
                main.clip.remove(0);
                main.fired = false;
            }
        }
    }

    //returns width
    public float getWidth()
    {
        return fgWidth;
    }

    //returns height
    public float getHeight()
    {
        return fgWidth;
    }

}