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
        bWidth=(float)(main.width*.01);
    }

    public void render() {
        main.fill(255);
        main.rect(pos.x, pos.y, bWidth, bWidth);

        detect();

    }

    //detects if touching player
    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(main.objects.get(i) instanceof Player)
            {
                Player temp=(Player)main.objects.get(i);

                //if x position is same as players
                if((pos.x<temp.pos.x && pos.x+getWidth()>temp.pos.x) || (pos.x+getWidth()>temp.pos.x && pos.x+getWidth()<temp.pos.x+temp.getWidth()))
                {
                    //if y position is same a players
                    if(pos.y>temp.pos.y-temp.getHeight() && pos.y+getWidth()<temp.pos.y)
                    {
                        //take away life
                        temp.lives--;
                        main.bulletArray.remove(this);
                    }
                }
            }
        }
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
