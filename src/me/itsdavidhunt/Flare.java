package me.itsdavidhunt;

import processing.core.*;

public class Flare extends PApplet{

    Main main;

    PVector pos;
    float goX;
    float goY;

    public Flare(float x,float y,float goX,float goY,Main _main)
    {
        main=_main;
        pos=new PVector(x,y);
        this.goX=goX;
        this.goY=goY;
    }

    public void render()
    {
        main.fill(0,255,0);
        main.rect(pos.x,pos.y,5,5);
        move();
    }

    int frame=0;
    public void move()
    {
        if(pos.x!=main.mouseX)
        {
            if(pos.y<main.height/2) {
                if (pos.x < goX) {
                    pos.x += .5;
                }
                if (pos.x > goX) {
                    pos.x -= .5;
                }
            }

        }
        if(pos.y!=goY)
        {
            if(main.fall==false) {
                if (pos.y < goY) {
                    pos.y += .5;
                }
                if (pos.x > goY) {
                    pos.y -= .5;
                }
                frame++;
            }
            if(frame>120)
            {
                main.fall=true;

                if (pos.y < main.height/2) {
                    pos.y += 1;
                }

            }


        }
    }
}
