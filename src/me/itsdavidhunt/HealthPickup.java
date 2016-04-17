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
        main.fill(0,255,0);
        main.stroke(0,255,0);
        main.rect(pos.x,pos.y,hWidth,-hWidth);

        addGravity(this);
        detect();
    }

    //detects if a player has walked over the pickup
    private void detect()
    {
        if (pos.y <= main.player.pos.y && pos.y + (hWidth / 4) > main.player.pos.y && pos.x <= main.player.pos.x + main.player.getWidth() && pos.x + hWidth >= main.player.pos.x) {
            applyTo();
            main.objects.remove(this);
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
