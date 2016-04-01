package me.itsdavidhunt;

import processing.core.*;

//Enemies will need to be dodged by the user
public class FlarePickup extends GameObject {

    private float fWidth;

    public FlarePickup(Main _main) {
        super(_main);
        fWidth=(float)(main.width*0.01);
    }

    //displays pickup on screen
    public void render() {
        main.fill(0,255,0);
        main.stroke(0,255,0);
        main.rect(pos.x,pos.y,fWidth,-fWidth);

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
                if (pos.y <= main.objects.get(i).pos.y && pos.y + (fWidth / 4) > main.objects.get(i).pos.y && pos.x <= main.objects.get(i).pos.x + main.objects.get(i).getWidth() && pos.x + fWidth >= main.objects.get(i).pos.x) {
                    main.objects.remove(this);
                    Flare flare = new Flare(main);
                    main.clip.add(flare);
                }
            }
        }
    }

    public float getWidth() {
        return fWidth;
    }
}
