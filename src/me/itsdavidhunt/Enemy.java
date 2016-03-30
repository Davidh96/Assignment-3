package me.itsdavidhunt;

import processing.core.*;

//Enemies will need to be dodged by the user
public class Enemy extends GameObject{

    float enWidth;
    float enHeight;
    boolean goLeft=true;

    public Enemy(Main _main) {
        super(_main);

        enHeight= -(float)(super.main.height*.03);
        enWidth= (float)(super.main.height*.02);
        pos=new PVector(((main.width/10)+10),main.height-50);
    }

    //renders enemy
    public void render() {
        super.main.fill(0);
        super.main.stroke(255);
        super.main.rect(pos.x,pos.y,enWidth,enHeight);

        patrol();
        detect();
        addGravity(this);

    }

    //controls the movement of an enemy
    public void patrol()
    {
        int moveDir=0;

        for(int i=0;i<main.blArray.size();i++) {

            //if player is on block
            if(main.blArray.get(i).pos.x<=pos.x+getWidth() && main.blArray.get(i).pos.x+main.blArray.get(i).getWidth()>=pos.x) {

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

    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(main.objects.get(i) instanceof Player)
            {
                if((pos.x==main.objects.get(i).pos.x || pos.x+enWidth==main.objects.get(i).pos.x) && pos.y+enHeight<=main.objects.get(i).pos.y) {
                    main.objects.get(i).pos.y = main.height / 2;
                }
            }
        }
    }

    public float getWidth() {
        return enWidth;
    }
}
