package me.itsdavidhunt;

import processing.core.*;

public class Flare extends PApplet{

    Main main;

    PVector pos;
    PVector goToPos;
    PVector pDest;
    int frame=0;
    float fWidth;
    float speed=(float).003;

    public Flare(float x,float y,float goX,float goY,Main _main)
    {
        main=_main;
        pos=new PVector(x,y);
        goToPos=new PVector(goX,goY);
        fWidth=(float)(main.width*0.01);
        pDest=new PVector(main.player.pos.x,main.player.pos.y);
    }

    public void render()
    {
        main.fill(0,255,0);
        main.rect(pos.x,pos.y,fWidth,fWidth);
        main.fill(0,255,0,100);
        main.stroke(0,255,0,100);
        main.ellipse(pos.x+(float)fWidth/2,pos.y+(float)fWidth/2,fWidth*10,fWidth*10);
        move();
    }


    public void move()
    {
            if(pos.y<main.height/2  && main.fall==false) {
                if (pos.x < goToPos.x) {
                    pos.x += main.width*speed;
                }
                if (pos.x > goToPos.x) {
                    pos.x -= main.width*speed;
                }
            }

            if(main.fall==false) {
                if (pos.y < goToPos.y) {
                    pos.y += main.height*speed;
                }
                if (pos.y > goToPos.y) {
                    pos.y -= main.height*speed;
                }
                frame++;
            }
        speed-=(speed*.001);
            if(frame==120 || pos.y<goToPos.y || (pos.x>goToPos.x-1 && pos.x<goToPos.x+1))
            {
                main.fall=true;

            }
            if(main.fall)
            {

                if (pos.y < main.height/2) {
                    pos.y += 1.5;

                    if(pDest.x>pos.x)
                    {
                        pos.x-=1;
                    }
                    else
                    {
                        pos.x+=1;
                    }

                }
            }



    }
}
