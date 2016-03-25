package me.itsdavidhunt;

import processing.core.*;

//Enemies will need to be dodged by the user
public class Enemy extends GameObject{

    float enWidth;
    float enHeight;

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

        addGravity(this);
    }


    public float getWidth() {
        return enWidth;
    }
}
