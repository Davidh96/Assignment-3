package me.itsdavidhunt;

import processing.core.*;

public class Bullet {

    Main main;
    PVector pos;
    PVector goPos;
    float bWidth;

    public Bullet(Main _main,float xPos,float yPos,float goXpos,float goYpos)
    {
        main=_main;
        pos=new PVector(xPos,yPos);
        goPos=new PVector(goXpos,goYpos);
        bWidth=(float)(main.width*.05);
    }

    public void render() {
        main.fill(255);
        main.rect(pos.x, pos.y, bWidth, bWidth);

    }
    
    public void move()
    {
        if(pos.x<goPos.x) {
            pos.x+=1;
        }
        if(pos.y<goPos.y)
        {
            pos.y+=1;
        }

        if(pos.x>goPos.x)
        {
            pos.x-=1;
        }

        if(pos.y>goPos.y)
        {
            pos.y-=1;
        }
    }

    public float getWidth()
    {
        return bWidth;
    }
}
