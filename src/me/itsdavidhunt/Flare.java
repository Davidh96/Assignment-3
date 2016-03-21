package me.itsdavidhunt;

import processing.core.*;

public class Flare extends PApplet{

    Main main;

    PVector pos;
    PVector goToPos;
    PVector pDest;
    int frame=0;
    float fWidth;
    float speed=(float).004;
    float radius=0;
    boolean grow=true;
    int grown=10;

    public Flare(float x,float y,float goX,float goY,Main _main)
    {
        main=_main;
        
        //the actual position of the flare
        pos=new PVector(x,y);

        //goToPos holds the positon that the user selected
        goToPos=new PVector(goX,goY);
        fWidth=(float)(main.width*0.01);

        //pDest will be used to see where the flare was shot in relation to the player
        pDest=new PVector(main.player.pos.x,main.player.pos.y);
    }

    public void render()
    {
        main.fill(0,255,0);
        main.rect(pos.x,pos.y,fWidth,fWidth);
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

        main.ellipse(pos.x+(float)fWidth/2,pos.y+(float)fWidth/2,fWidth*10-radius,fWidth*10-radius);

        move();
    }


    public void move()
    {
        //if the flare is not falling
        if(main.fall==false) {

            //if the flare hasnt touched the ground
            if (pos.y < main.height / 2 ) {
                //move towards the chosen X value

                if (pos.x < goToPos.x) {
                    pos.x += main.width * speed;
                }
                if (pos.x > goToPos.x) {
                    pos.x -= main.width * speed;
                }
            }

            //move towards the chosen Y value

            if (pos.y < goToPos.y) {
                pos.y += main.height * speed;
            }
            if (pos.y > goToPos.y) {
                pos.y -= main.height * speed;
            }
            frame++;

            //flare slows down as it goes higher
            speed-=(speed*.01);
        }


            //if after certain amount of time, the flare has reached the chose Y value or chosen X value then drop!
            if(frame==100 || pos.y<goToPos.y || (pDest.x>pos.x && goToPos.x>pos.x) || (pDest.x<pos.x && goToPos.x-fWidth<pos.x))
            {
                main.fall=true;
            }

            //controls how a flare falls, trying to make it look realistic
            if(main.fall)
            {
                if(pDest.x>pos.x && pos.y < main.height / 2)
                {
                    //speeds up the fall of the flare when going left
                    pos.x-=main.width*.001;
                }
                if(pDest.x<pos.x && pos.y < main.height / 2)
                {
                    //speeds up the fall of the flare when going right
                    pos.x+=main.width*.001;
                }

            }



    }
}
