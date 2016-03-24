package me.itsdavidhunt;

import processing.core.*;

public class Block extends GameObject {

    float blWidth;
    float blHeight;

    public Block(Main _main) {
        super(_main);
    }

    public void render() {

        blHeight= (float)(super.main.height*.05);
        blWidth= (float)(super.main.height*.05);

        main.fill(255,0,0);
        super.main.rect(pos.x,pos.y,blWidth,blHeight);

        detect();

    }

    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            if(pos.y<=main.objects.get(i).pos.y && pos.y+(blWidth/4)>main.objects.get(i).pos.y && pos.x<=main.objects.get(i).pos.x && pos.x+blWidth>=main.objects.get(i).pos.x )
            {
                main.objects.get(i).inAir=false;
            }
        }
    }

    public float getWidth()
    {
        return blWidth;
    }

}
