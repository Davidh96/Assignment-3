package me.itsdavidhunt;

import processing.core.*;

//Enemies will need to be dodged by the user
public class Enemy extends GameObject{

    float enWidth;
    float enHeight;
    boolean goLeft=false;

    public Enemy(Main _main) {
        super(_main);

        enHeight= -(float)(super.main.height*.03);
        enWidth= (float)(super.main.height*.02);
        pos=new PVector((main.width/10),main.height-50);
    }

    //renders enemy
    public void render() {
        super.main.fill(255,0,0);
        super.main.stroke(255,0,0);
        super.main.rect(pos.x,pos.y,enWidth,enHeight);

        patrol();
        addGravity(this);

    }

    //controls the movement of an enemy
    public void patrol()
    {
        for(int i=0;i<main.blArray.size();i++)
        {
            //if enemy is on block
            if (pos.x + getWidth() <= main.blArray.get(i).pos.x + main.blArray.get(i).getWidth() && pos.x >= main.blArray.get(i).pos.x) {

                if(goLeft) {
                    if (pos.x + getWidth() <= main.blArray.get(i).pos.x + main.blArray.get(i).getWidth()) {
                        goLeft = true;
                    }
                    if (pos.x + getWidth() >= main.blArray.get(i).pos.x + main.blArray.get(i).getWidth()) {
                        goLeft = false;
                    }
                }
                else
                {
                    if(pos.x<=main.blArray.get(i).pos.x)
                    {
                        goLeft=true;
                    }
                }
                if(goLeft)
                {
                    pos.x++;
                }
                else
                {
                    pos.x--;
                }
            }

        }
    }

    public float getWidth() {
        return enWidth;
    }
}
