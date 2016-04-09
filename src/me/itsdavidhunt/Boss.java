package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Boss {

    Main main;
    PVector pos;
    int headNum;

    public Boss(Main _main,int headNum)
    {
        main=_main;
        float sectorW=main.width/3;
        float randHeight=main.height/2;
        this.headNum=headNum;
        pos=new PVector((sectorW*headNum)-sectorW/2,randHeight);
    }

    //renders boss head on screen
    public void render()
    {
        main.fill(255,0,0);

        main.ellipse(pos.x,pos.y,20,20);

    }

    //moves boss
    public void movement()
    {

    }

    //detects if a boss head has been hit
  public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(main.objects.get(i) instanceof Flare) {
                //if touching/in flare
                Flare temp=(Flare)main.objects.get(i);

            }
        }
    }
}
