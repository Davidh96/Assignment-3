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

        super.main.rect(pos.x,pos.y,blWidth,blHeight);

        detect();
    }

    public void detect()
    {
        for(int i=0;i<super.main.objects.size();i++)
        {
            if((super.main.objects.get(i).pos.y>=pos.y && super.main.objects.get(i).pos.y<=pos.y+blWidth/2) && (super.main.objects.get(i).pos.x>=pos.x && super.main.objects.get(i).pos.x<=pos.x+blWidth))
            {
                super.main.objects.get(i).inAir=false;
                super.main.objects.get(i).pos.y=pos.y;
            }
            else
            {
                super.main.objects.get(i).inAir=true;
            }
        }
    }
}
