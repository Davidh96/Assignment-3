package me.itsdavidhunt;

import processing.core.*;

//are the projectiles that are fired at the player
public class Bullet {

    Main main;
    public PVector pos;
    public PVector goPos;
    private float bWidth;
    private int time=0;
    private PImage img;

    public Bullet(Main _main,float xPos,float yPos,float goXpos,float goYpos)
    {
        main=_main;
        pos=new PVector(xPos,yPos);
        goPos=new PVector(goXpos,goYpos);
        bWidth=(float)(main.width*.03);
        img=main.loadImage("Bullet.png");
    }

    //renders bullet on screen
    public void render() {

        main.image(img,pos.x,pos.y,bWidth,bWidth);

        if(time>60*8)
        {
            FlarePickup flare=new  FlarePickup(main);
            flare.pos=new PVector(pos.x,pos.y);
            main.objects.add(flare);
            main.bulletArray.remove(this);
        }
        time++;

        detect();
        move();
    }

    //detects if touching player
    public void detect()
    {
        //get the distance of bullet from the player
        float dist = PVector.dist(pos, main.player.pos);
        //if bullet in player
        if(dist<bWidth)
        {
            //take away a life
            main.player.lives--;
            //destroy itself
            main.bulletArray.remove(this);
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
