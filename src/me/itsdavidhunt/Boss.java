package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Boss {

    Main main;
    PVector pos;
    PVector goPos;
    float sectorW;
    int headNum;
    PImage img;
    float bWidth;
    boolean moved=true;
    int health;
    int maxhealth;
    private ArrayList<Bullet>bulletArray=new ArrayList<Bullet>();

    public Boss(Main _main,int headNum)
    {
        main=_main;
        sectorW=main.width/3;
        float randHeight=main.height/2;
        this.headNum=headNum;
        pos=new PVector((sectorW*headNum)-sectorW/2,randHeight);
        bWidth=(float)(main.width*.08);
        img=main.loadImage("Boss.png");

        health=100;
        maxhealth=health;
    }

    //renders boss head on screen
    public void render()
    {
        main.fill(255,0,0);
        main.image(img,pos.x,pos.y,bWidth,bWidth);

        //healthbar
        main.fill(255,0,0);
        main.rect(pos.x,pos.y-bWidth/2,main.map(health,0,maxhealth,0,bWidth),10);

        detect();
        shoot();
    }

    //moves boss
    public void movement()
    {
        if(moved==false)
        {
            //moving is used to check if the head has stopped moving
            int moving=0;

            if(pos.x<goPos.x) {
                pos.x+=1;
                moving++;
            }
            if(pos.y<goPos.y)
            {
                pos.y+=1;
                moving++;
            }

            if(pos.x>goPos.x)
            {
                pos.x-=1;
                moving++;
            }

            if(pos.y>goPos.y)
            {
                pos.y-=1;
                moving++;
            }

            //if stopped moving choose new random position to go to
            if(moving==0)
            {
                moved=true;
            }
        }
        //if the boss head has reached its random destination, give it a new one
        if(moved)
        {
            //give random x position for the boss head to go to
            int xPos=(int)main.random(sectorW*(headNum-1),sectorW*headNum);
            //give random y position for the boss head to go to
            int yPos=(int)main.random((float)(main.height*.1),(float)(main.height*.5));
            goPos=new PVector(xPos,yPos);
            moved=false;
        }
    }

    int time=0;
    public void shoot()
    {

        //every 5 seconds
        if(time==60*5) {
            //create new bullet
            Bullet bullet = new Bullet(main, pos.x, pos.y, main.player.pos.x, main.player.pos.y);
            //add to boss clip
            bulletArray.add(bullet);
            time=0;
        }
        time++;

        //renders bullets
        for(int i=0;i<bulletArray.size();i++)
        {
            bulletArray.get(i).render();
            bulletArray.get(i).move();
        }

    }

    //detects if a boss head has been hit
  public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(main.objects.get(i) instanceof Flare) {
                //if touching/in flare
                Flare temp=(Flare)main.objects.get(i);

                float dist = PVector.dist(pos, temp.pos);
                if(dist<bWidth)
                {
                    health--;

                    if(health<1)
                    {
                        main.heads.remove(this);
                    }
                }

            }
        }
    }
}
