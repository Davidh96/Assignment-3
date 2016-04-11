package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Exit {

    Main main;

    public PVector pos;
    private float eHeight;
    private float eWidth;

    public Exit(Main _main) {
        main=_main;
        eHeight=(float)(main.height*.06);
        eWidth=(float)(main.width*.03);
    }

    public void render()
    {
        main.fill(255);
        main.stroke(255);
        main.rect(pos.x,pos.y,-eWidth,-eHeight);
    }

    //detects if player touching exit
    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            //if player
            if(main.objects.get(i) instanceof Player)
            {
                Player temp=(Player)main.objects.get(i);
                //if touching exit
                if((pos.x>=temp.pos.x && pos.x+getWidth() <= temp.pos.x)||(pos.x>=temp.pos.x+temp.getWidth() && pos.x+getWidth() <= temp.pos.x+temp.getWidth()))
                {
                    if(pos.y>=temp.pos.y && pos.y+getWidth()<=temp.pos.y) {
                        //reset world
                        main.world.resetWorld = true;
                        //next level
                        main.world.level++;
                    }
                }
            }
        }
    }

    //returns width
    public float getWidth()
    {
        return -eWidth;
    }

    public float getHeight()
    {
        return eHeight;
    }

}
