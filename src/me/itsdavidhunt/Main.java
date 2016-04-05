package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet {
	
	public Player player;
    public FlareGun gun;
    public Enemy enemy;
    public FlarePickup fPickup3;
    public FlarePickup fPickup;
    public playerHUD HUD;
    public World world;

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
        enemy.pos=new PVector(250,250);
        objects.add(player);

        fPickup3=new FlarePickup(this);
        fPickup3.pos = new PVector(120,50);
        fPickup=new FlarePickup(this);
        fPickup.pos = new PVector(120,50);

        objects.add(fPickup3);
        objects.add(fPickup);

        objects.add(enemy);

        HUD=new playerHUD(this);

        world=new World(this);

    }
    

    public void draw() {
    	background(0);
        play();
    }    
    
    //This method will control all the classes for playing the game
    public void play()
    {
    	player.move();
        HUD.display();

        for(int i=0;i<objects.size();i++)
        {
            objects.get(i).render();
        }

        player.flareAim();

        world.generate();

    }

    public void mouseReleased()
    {
        fired=true;
    }
}