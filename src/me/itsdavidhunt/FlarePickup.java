package me.itsdavidhunt;

import processing.core.*;

//need to be pick upped to fire flres
public class FlarePickup extends GameObject {

    private float fWidth;

    public FlarePickup(Main _main) {
        super(_main);
        fWidth=(float)(main.width*0.01);
    }

    //displays pickup on screen
    public void render() {
        main.fill(0,0,255);
        main.stroke(0,0,255);
        main.rect(pos.x,pos.y,fWidth,-fWidth);

        //object is now affected by gravity
        addGravity();
        detect();
    }

    //detects if a player has walked over the pickup
    private void detect()
    {
        //if touching/in player
        if (pos.y <= main.player.pos.y && pos.y + (fWidth / 4) > main.player.pos.y && pos.x <= main.player.pos.x + main.player.getWidth() && pos.x + fWidth >= main.player.pos.x) {
            applyTo();
            main.objects.remove(this);
        }
    }

    public void applyTo()
    {
        Flare flare = new Flare(main);
        main.clip.add(flare);
    }

    //returns width
    public float getWidth() {
        return fWidth;
    }
    //returns height
    public float getHeight() {
        return fWidth;
    }
}
