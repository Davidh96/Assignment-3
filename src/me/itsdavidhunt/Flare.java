package me.itsdavidhunt;

import processing.core.*;

public class Flare extends GameObject{


    private PVector goToPos;
    private PVector pDest;
    private float fWidth;
    private float speed=(float).004;
    private float radius=0;
    private boolean grow=true;
    private int grown=10;

    //these booleans are used so that the flare goes around where the players asks it to, not exactly to the point chosen. This makes the flare physics a little bit more realistic!
    private boolean goLeft=false;
    private boolean goRight=false;
    private boolean goUp=false;

    public Flare(Main _main)
    {
        super(_main);
        Flare(main.gun.pos.x,main.player.pos.y-main.player.getHeight(),main.mouseX,main.mouseY);
    }

    public void Flare(float x,float y,float goX,float goY)
    {
        //the actual position of the flare
        pos=new PVector(x,y);

        //goToPos holds the positon that the user selected
        goToPos=new PVector(goX,goY);
        fWidth=(float)(main.width*0.01);

        //pDest will be used to see where the flare was shot in relation to the player
        pDest=new PVector(main.player.pos.x,main.player.pos.y);
    }

    public float getWidth()
    {
        return fWidth;
    }

    //renders the flare
    public void render()
    {
        main.fill(0,255,0);
        main.stroke(0,255,0);
        main.rect(pos.x,pos.y,fWidth,-fWidth);
        main.fill(0,255,0,100);
        main.stroke(0,255,0,100);

        //makes the flare look like its flickering
        if(grow)
        {
            radius+=.5;

            if(radius>grown)
            {
                grow=false;
            }
        }
        else
        {
            radius-=.5;
            if(radius<0)
            {
                grow=true;
            }
        }

        //if flare goes off screen, remove it
        if(pos.y-getWidth()>main.height)
        {
            main.clip.remove(this);
        }

        main.ellipse(pos.x+(float)fWidth/2,pos.y+(float)(-fWidth)/2,fWidth*10-radius,fWidth*10-radius);

        move();
        addGravity(this);

    }

    //controls the flares movement
    public void move()
    {
        if(inAir==true) {
            //if going right
            if (goToPos.x > pDest.x) {
                goRight = true;
            }

            //if going left
            if (goToPos.x < pDest.x) {
                goLeft = true;
            }

            //continue to go left
            if (goLeft) {
                pos.x -= main.width * speed;
            }

            //continue to go Right
            if (goRight) {
                pos.x += main.width * speed;
            }

            //move towards the chosen Y value
            if (pos.y > goToPos.y) {
                goUp = true;
            }

            //continue going up
            if (goUp) {
                pos.y -= main.height * speed;
            }

            //flare slows down as it goes higher
            speed -= (speed * .01);

        }


    }
}
