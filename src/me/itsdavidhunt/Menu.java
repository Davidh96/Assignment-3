package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Menu {

    Main main;

    public Menu(Main _main)
    {
        main = _main;
    }

    public void display() {
        main.fill(255);
        main.textAlign(main.CENTER,main.CENTER);
        main.textSize((float)(main.width*.25));
        main.text("FLARE",main.width/2,main.height/4);

        main.rect(0,main.height/2,main.width,main.width/8);
        main.fill(0);
        main.textSize((float)(main.width*.05));
        main.text("PLAY",main.width/2,(float)(main.height-main.height/2.5));

        main.fill(255);
        main.textAlign(main.CENTER,main.CENTER);
        main.textSize((float)(main.width*.05));
        main.text("BY DAVID HUNT",main.width/2,(float)(main.height-main.height/5));
    }

    public void interact()
    {
        if(main.mouseY>main.height/2 && main.mouseY<main.height/2+main.width/8)
        {
            if(main.mousePressed)
            {
                main.playGame=true;
            }
        }
    }
}
