package me.itsdavidhunt;

import processing.core.*;

public class playerHUD {

    Main main;

    public playerHUD(Main _main)
    {
        main = _main;
    }

    public void display()
    {
        main.textSize((float)(main.height*.03));
        main.text("Lives: " + main.player.lives,(float)(main.width*.01),(float)(main.height*.025));
        main.text("Flares: " + main.clip.size(),(float)(main.width*.01),(float)(main.height*.05));
    }
}
