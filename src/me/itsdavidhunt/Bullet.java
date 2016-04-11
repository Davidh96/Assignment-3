package me.itsdavidhunt;

import processing.core.*;

public class Bullet {

    Main main;
    PVector pos;
    PVector goPos;
    float bWidth;
    int time=0;

    public Bullet(Main _main,float xPos,float yPos,float goXpos,float goYpos)
    {
        main=_main;
        pos=new PVector(xPos,yPos);
        goPos=new PVector(goXpos,goYpos);
        bWidth=(float)(main.width*.01);
    }

    //renders bullet on screen
    public void render() {
        main.fill(255,0,0);
        main.stroke(255,0,0);
        main.rect(pos.x, pos.y, bWidth, bWidth);

        //bulletArray.remove(this);

        time++;

        detect();


    }

    //detects if touching player
    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            //if player
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
                        //temp.lives--;
                        //main.bulletArray.remove(this);
                    }
                }
            }
        }
    }

    //moves the bullet
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

    //returns the width of a bullet
    public float getWidth()
    {
        return bWidth;
    }
}
