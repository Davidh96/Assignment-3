package me.itsdavidhunt;

import processing.core.*;

public class HealthPickup extends GameObject implements PickUp {

    private float hWidth;

    public HealthPickup(Main _main) {
        super(_main);
        hWidth=(float)(main.width*0.01);
    }

    //displays pickup on screen
    public void render() {
        main.fill(0,0,255);
        main.stroke(0,255,255);
        main.rect(pos.x,pos.y,hWidth,-hWidth);

        addGravity(this);
        detect();
    }

    //detects if a player has walked over the pickup
    private void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(main.objects.get(i) instanceof Player) {
                //if touching/in player
                Player temp=(Player)main.objects.get(i);
                if (pos.y <= temp.pos.y && pos.y + (hWidth / 4) > temp.pos.y && pos.x <= temp.pos.x + temp.getWidth() && pos.x + hWidth >= temp.pos.x) {
                    applyTo();
                    main.objects.remove(this);
                }
            }
        }
    }

    public void applyTo()
    {
        main.player.lives++;
    }

    //returns width
    public float getWidth() {
        return hWidth;
    }
    //returns height
    public float getHeight() {
        return hWidth;
    }
}
