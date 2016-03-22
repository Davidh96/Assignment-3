package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet {
	
	Player player;
    FlareGun gun;

    ArrayList<Flare> clip=new ArrayList<Flare>();
    ArrayList<GameObject>objects=new ArrayList<GameObject>();

    public boolean fired=true;
    
    public static void main(String[] args) {
        String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());
    }

    public void settings(){
    fullScreen();
    //size(500,500);
    }
    
    public void setup()
    {
    	player=new Player(this);
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

        Block ground=new Block(this);
        ground.pos=new PVector(width/2,height/2);
        ground.render();

    }
}