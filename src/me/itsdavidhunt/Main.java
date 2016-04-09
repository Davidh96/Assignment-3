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
    public Menu menu;

    public ArrayList<Flare> clip=new ArrayList<Flare>();
    public ArrayList<GameObject>objects=new ArrayList<GameObject>();
    public ArrayList<Block>blArray=new ArrayList<Block>();

    public boolean fired=false;
    public boolean playGame=false;

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
        menu=new Menu(this);

        player=new Player(this);

        HUD=new playerHUD(this);

        world=new World(this);
    }
    

    public void draw() {
    	background(0);
        if(!playGame) {
            menu.display();
            menu.interact();
        }
        if(playGame) {
            play();
        }
    }    

    int count=0;
    //This method will control all the classes for playing the game
    public void play()
    {

        if(world.level<5 && player.lives>0) {
            player.move();
            HUD.display();

            //renders all game objects that are not enemies
            for (int i = 0; i < objects.size(); i++) {
                if(!(objects.get(i) instanceof Enemy))
                {
                    objects.get(i).render();
                }
            }

            player.flareGunSetup();

            world.generate();

            //render enemies
            for (int i = 0; i < objects.size(); i++) {
                if((objects.get(i) instanceof Enemy))
                {
                    objects.get(i).render();
                }
            }

            player.flareAim();
        }
        //load final level
        else if(world.level==5)
        {
            //world.finalLevel();
            world.level++;
        }
        //displays end screen
        else if(world.level==6 || player.lives<1)
        {
            fill(255);
            textAlign(CENTER,CENTER);
            textSize((float)(width*.25));
            text("FIN",width/2,height/4);
            textSize((float)(width*.05));
            //if the player dies
            if(player.lives<1)
            {
                text("You have lost the fight with the dark",width/2,height/2);
            }
            //if the player completes game
            if(world.level==6)
            {
                text("You have beaten the darkness",width/2,height/2);
            }

        }

    }

    public void mouseReleased()
    {
        fired = true;
    }
}