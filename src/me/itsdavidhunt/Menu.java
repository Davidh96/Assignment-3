package me.itsdavidhunt;

import processing.core.*;

//this class controls all the menu functions
public class Menu extends PApplet {

    Main main;

    //controls button colour
    int color=255;

    public Menu(Main _main)
    {
        main = _main;
    }

    //displays start menu
    public void display() {
        //title
        main.fill(255);
        main.stroke(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .25));
        main.text("FLARE", main.width / 2, main.height / 4);

        //play button
        main.fill(color);
        main.rect(0, main.height / 2, main.width, main.width / 8);
        main.fill(0);
        main.textSize((float) (main.width * .05));
        main.text("PLAY", main.width / 2, (float) (main.height - main.height / 2.5));

        //credits
        main.fill(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .02));
        main.text("BY DAVID HUNT", main.width / 2, (float) (main.height - main.height / 5));
    }

    //controls what is displayed on the end screen
    public void endScreen()
    {
        //title
        main.fill(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .1));

        //if player has completed game
        if(main.win) {
            main.text("YOU WON", main.width / 2, main.height / 4);
        }
        //player has died
        else {
            main.text("YOU FAILED", main.width / 2, main.height / 4);
        }


        //play button
        main.fill(color);
        main.stroke(255);
        main.rect(0, main.height / 2, main.width, main.width / 8);
        main.fill(0);
        main.textSize((float) (main.width * .05));
        main.text("REPLAY", main.width / 2, (float) (main.height - main.height / 2.5));

        //displays how long the player had played
        main.fill(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .02));
        main.text("YOU PLAYED FOR: " + main.timer/60 +" SECONDS", main.width / 2, (float) (main.height - main.height / 5));
    }

    //controls the interaction with the menu buttons
    public void interact()
    {
        //if hovering over play button
        if(main.mouseY>main.height/2 && main.mouseY<main.height/2+main.width/8)
        {
            //makes play button turn grey
            color=220;

            if(main.mousePressed)
            {
                main.endGame=false;
                main.finalRound=false;
                main.world.initial=true;
                main.playGame=true;
                main.win=true;
                main.timer=0;
            }
        }
        else
        {
            color=255;
        }
    }


}
