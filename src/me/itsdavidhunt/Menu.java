package me.itsdavidhunt;

import processing.core.*;

public class Menu {

    Main main;

    int color=255;

    public Menu(Main _main)
    {
        main = _main;
    }

    //displays menu
    public void display() {
        //title
        main.fill(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .25));
        main.text("FLARE", main.width / 2, main.height / 4);

        //play putton
        main.fill(color);
        main.rect(0, main.height / 2, main.width, main.width / 8);
        main.fill(0);
        main.textSize((float) (main.width * .05));
        main.text("PLAY", main.width / 2, (float) (main.height - main.height / 2.5));

        main.fill(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .05));
        main.text("BY DAVID HUNT", main.width / 2, (float) (main.height - main.height / 5));
    }

    public void endScreen()
    {
        //title
        main.fill(255);
        main.textAlign(main.CENTER, main.CENTER);
        main.textSize((float) (main.width * .1));

        if(main.win) {
            main.text("YOU WON", main.width / 2, main.height / 4);
        }
        else {
            main.text("YOU FAILED", main.width / 2, main.height / 4);
        }


        //play putton
        main.fill(color);
        main.rect(0, main.height / 2, main.width, main.width / 8);
        main.fill(0);
        main.textSize((float) (main.width * .05));
        main.text("REPLAY", main.width / 2, (float) (main.height - main.height / 2.5));
    }

    //controls the interaction with the menu buttons
    public void interact()
    {
        //if hovering over play button
        if(main.mouseY>main.height/2 && main.mouseY<main.height/2+main.width/8)
        {
            color=220;

            Flare flare=new Flare(main);
            flare.pos=new PVector(main.mouseX,main.mouseY);
            flare.inAir=true;
            flare.pDest=new PVector(main.mouseX,main.mouseY);

            flare.goToPos=new PVector(main.mouseX,main.mouseY);
            flare.render();

            if(main.mousePressed)
            {
                main.endGame=false;
                main.finalRound=false;
                main.world.initial=true;
                main.playGame=true;
                main.win=true;

            }
        }
        else
        {
            color=255;
        }
    }


}
