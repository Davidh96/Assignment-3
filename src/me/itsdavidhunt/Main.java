package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet {
	
	public Player player;
    public FlareGun gun;
    public Block ground;
    public Enemy enemy;
    public FlarePickup fPickup3;
    public FlarePickup fPickup;

    public ArrayList<Flare> clip=new ArrayList<Flare>();
    public ArrayList<GameObject>objects=new ArrayList<GameObject>();
    public ArrayList<Block>blArray=new ArrayList<Block>();

    public boolean fired=true;

    public static void main(String[] args) {
        String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());
    }

    public void settings(){
    //fullScreen();
    size(800,500);
    }
    
    public void setup()
    {
    	player=new Player(this);
       enemy=new Enemy(this);
        objects.add(player);

        fPickup3=new FlarePickup(this);
        fPickup3.pos = new PVector(120,50);
        fPickup=new FlarePickup(this);
        fPickup.pos = new PVector(120,50);

        objects.add(fPickup3);
        objects.add(fPickup);

        objects.add(enemy);

    }
    

    public void draw() {
    	background(0);
        play();
    }    
    
    //This method will control all the classes for playing the game
    public void play()
    {

    	player.move();

        println(fPickup.inAir);

        for(int i=0;i<objects.size();i++)
        {
            objects.get(i).render();
        }

        player.flareAim();
        /*for(int i=0;i<clip.size();i++)
        {
            clip.get(i).render();
        }*/

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
        //enemy.render();

    }

    public void mouseReleased()
    {
        fired=true;
    }
}