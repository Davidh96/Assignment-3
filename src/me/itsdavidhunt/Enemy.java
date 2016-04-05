package me.itsdavidhunt;

import processing.core.*;

//Enemies will need to be dodged by the user
public class Enemy extends GameObject{

    private float enWidth;
    private float enHeight;
    private boolean goLeft=true;

    public Enemy(Main _main) {
        super(_main);

        enHeight= -(float)(main.height*.03);
        enWidth= (float)(main.height*.02);
    }

    //renders enemy
    public void render() {
        main.fill(0);
        main.stroke(255);
        main.rect(pos.x,pos.y,enWidth,enHeight);

        if(inAir==false) {
            patrol();
        }

        detect();
        addGravity(this);

    }

    //controls the movement of an enemy
    public void patrol()
    {
        int moveDir=0;

        for(int i=0;i<main.blArray.size();i++) {

            //if player is on block
            if(main.blArray.get(i).pos.x<pos.x+getWidth() && main.blArray.get(i).pos.x+main.blArray.get(i).getWidth()>pos.x ) {
                //if the current position of enemy is greater than the left edge of the block and they have reached the right end of the block
                if (pos.x >= main.blArray.get(i).pos.x && goLeft) {
                    //move left
                    moveDir = -1;
                    //if the enemy has reached the ned of the of the block on the left
                    if (pos.x <= main.blArray.get(i).pos.x) {

                        goLeft = false;
                    }
                }

                //if the current position of enemy is less than the right edge of the block and they have reached the left end of the block
                if(pos.x <= main.blArray.get(i).pos.x + main.blArray.get(i).getWidth() && goLeft == false) {
                    //move right

                    moveDir = 1;
                    //if the enemy has reached the ned of the of the block on the right
                    if (pos.x + getWidth() >= main.blArray.get(i).pos.x + main.blArray.get(i).getWidth()) {
                        goLeft = true;
                    }
                }

                pos.x = pos.x + moveDir;
            }
        }

    }

    //detects if the enemy has hit a player
    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(main.objects.get(i) instanceof Player)
            {
                Player pTemp=(Player)main.objects.get(i);
                //if the player is touching/in the enemy
                if(((pos.x<=pTemp.pos.x && pos.x+getWidth()>=pTemp.pos.x) || (pos.x+getWidth()>=pTemp.pos.x && pos.x<=pTemp.pos.x+pTemp.getWidth()))&& pos.y+enHeight<=pTemp.pos.y && pos.y>=pTemp.pos.y) {
                    pTemp.pos.y = main.height / 2;
                    pTemp.pos.x = main.width / 2;
                    pTemp.speed = (float).001;
                    pTemp.lives-=1;
                }
            }
        }
    }

    public float getWidth() {
        return enWidth;
    }

    public float getHeight() {
        return enHeight;
    }
}
