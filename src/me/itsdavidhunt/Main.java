package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet {
	
	Player player;
    FlareGun gun;
    Block ground;
    Enemy enemy;

    ArrayList<Flare> clip=new ArrayList<Flare>();
    ArrayList<GameObject>objects=new ArrayList<GameObject>();
    ArrayList<Block>blArray=new ArrayList<Block>();

    public boolean fired=true;

    public static void main(String[] args) {
        String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());
    }

    public void settings(){
    //fullScreen();
    size(500,500);
    }
    
    public void setup()
    {
    	player=new Player(this);
        enemy=new Enemy(this);
    }
    

    public void draw() {
    	background(0);
        play();
    }    
    
    //This method will control all the classes for playing the game
    public void play()
    {

    	player.render();
    	player.move();
        objects.add(player);
    	player.flareAim();

        for(int i=0;i<clip.size();i++)
        {
            clip.get(i).render();
        }

        if(blArray.size()<10)
        {
            for(int i=0;i<10;i++)
            {
                ground=new Block(this);
                ground.pos=new PVector((width/22)*i,height-10);
                blArray.add(ground);
            }
        }
        for(int i=0;i<blArray.size();i++)
        {
            blArray.get(i).render();
        }
        enemy.render();
        objects.add(enemy);

    }
}