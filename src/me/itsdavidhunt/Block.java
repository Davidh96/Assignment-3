package me.itsdavidhunt;

public class Block extends GameObject {

    private float blWidth;
    private float blHeight;
    public int lane;

    public Block(Main _main) {
        super(_main);
        blHeight= (float)(main.height*.05);
        blWidth= (float)(main.width/10);
    }

    //renders the block on screen
    public void render() {

        main.fill(255);
        main.stroke(0);
        main.rect(pos.x,pos.y,blWidth,blHeight);

        detect();

    }

    //detects if their is an object from ojbects. This will cause an objects to stop falling
    public void detect()
    {
        for(int i=0;i<main.objects.size();i++)
        {
            //if the object is on top of the block
            if(pos.y<=main.objects.get(i).pos.y && pos.y+(blHeight/4)>main.objects.get(i).pos.y && pos.x<=main.objects.get(i).pos.x+main.objects.get(i).getWidth() && pos.x+blWidth>=main.objects.get(i).pos.x)
            {
                main.objects.get(i).inAir=false;
                main.objects.get(i).pos.y=pos.y;
            }
        }
    }

    public float getWidth()
    {
        return blWidth;
    }
    public float getHeight()
    {
        return blHeight;
    }

}
