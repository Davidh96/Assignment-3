package me.itsdavidhunt;

import processing.core.*;

public class Flare extends GameObject{


    PVector goToPos;
    PVector pDest;
    int frame=0;
    float fWidth;
    float speed=(float).004;
    float radius=0;
    boolean grow=true;
    int grown=10;

    public Flare(Main _main)
    {
        super(_main);
        Flare(super.main.gun.pos.x,super.main.gun.pos.y,super.main.mouseX,super.main.mouseY);
    }

    public void Flare(float x,float y,float goX,float goY)
    {

        //the actual position of the flare
        pos=new PVector(x,y);

        //goToPos holds the positon that the user selected
        goToPos=new PVector(goX,goY);
        fWidth=(float)(super.main.width*0.01);

        //pDest will be used to see where the flare was shot in relation to the player
        pDest=new PVector(super.main.player.pos.x,super.main.player.pos.y);
    }

    public void render()
    {
        super.main.fill(0,255,0);
        super.main.stroke(0,255,0);
        super.main.rect(pos.x,pos.y,fWidth,fWidth);
        super.main.fill(0,255,0,100);
        super.main.stroke(0,255,0,100);

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

        super.main.ellipse(pos.x+(float)fWidth/2,pos.y+(float)fWidth/2,fWidth*10-radius,fWidth*10-radius);

        move();
    }


    public void move()
    {
        //if the flare is not falling
        if(super.main.fall==false) {

            //if the flare hasnt touched the ground
            if (pos.y < super.main.height / 2 ) {
                //move towards the chosen X value

                if (pos.x < goToPos.x) {
                    pos.x += super.main.width * speed;
                }
                if (pos.x > goToPos.x) {
                    pos.x -= super.main.width * speed;
                }
            }

            //move towards the chosen Y value

            if (pos.y < goToPos.y) {
                pos.y += super.main.height * speed;
            }
            if (pos.y > goToPos.y) {
                pos.y -= super.main.height * speed;
            }
            frame++;

            //flare slows down as it goes higher
            speed-=(speed*.01);
        }


        //if after certain amount of time, the flare has reached the chose Y value or chosen X value then drop!
        if(frame==100 || pos.y<goToPos.y || (pDest.x>pos.x && goToPos.x>pos.x) || (pDest.x<pos.x && goToPos.x-fWidth<pos.x))
        {
            super.main.fall=true;
        }

        //controls how a flare falls, to make it look more realistic
        if(super.main.fall)
        {
            //if the flare is falling to the left
            if(pDest.x>pos.x && pos.y < super.main.height / 2)
            {
                pos.x-=main.width*.001;
            }
            //if the flare is falling to the right
            if(pDest.x<pos.x && pos.y < super.main.height / 2)
            {
                pos.x+=super.main.width*.001;
            }

            //if the flare is in th air
            if (pos.y < super.main.height/2) {

                //drop flare
                pos.y +=super.main.height*speed ;

                //controls which direction to drop flare
                //if on left
                if(pDest.x>pos.x)
                {
                    pos.x-=1;
                }
                //if on right
                else {
                    pos.x += 1;
                }

            }

            //the flare drops faster the longer it is in the air
            speed+=(speed*.025);
        }



    }
}
