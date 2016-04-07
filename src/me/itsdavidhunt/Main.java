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
    //public Menu menu;

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

        enemy=new Enemy(this);
        enemy.pos=new PVector(250,250);

        player=new Player(this);

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
        //menu.display();
        play();
    }    
    
    //This method will control all the classes for playing the game
    public void play()
    {
        if(world.level<5 && player.lives>0) {
            player.move();
            HUD.display();

            for (int i = 0; i < objects.size(); i++) {
                objects.get(i).render();
            }

            player.flareAim();

            world.generate();
        }
        else if(world.level==5)
        {
            world.finalLevel();
            //world.level++;
        }
        else if(world.level==6 || player.lives<1)
        {
            fill(255);
            textAlign(CENTER,CENTER);
            textSize((float)(width*.25));
            text("FIN",width/2,height/4);
            textSize((float)(width*.05));
            if(player.lives<1)
            {
                text("You have lost the fight with the dark",width/2,height/2);
            }
            if(world.level==6)
            {
                text("You have beaten the darkness",width/2,height/2);
            }

        }

    }

    public void mouseReleased()
    {
        fired=true;
    }
}