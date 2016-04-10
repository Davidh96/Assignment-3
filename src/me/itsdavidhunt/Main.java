package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet {

	public Player player;
    public FlareGun gun;
    public playerHUD HUD;
    public World world;
    public Menu menu;


    public ArrayList<Flare> clip=new ArrayList<Flare>();
    public ArrayList<GameObject>objects=new ArrayList<GameObject>();
    public ArrayList<Block>blArray=new ArrayList<Block>();
    public ArrayList<Boss>heads=new ArrayList<Boss>();
    ArrayList<Bullet>bulletArray=new ArrayList<Bullet>();

    public boolean fired=false;
    public boolean playGame=false;
    public boolean endGame=false;
    public boolean finalRound=true;

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

        for(int i=0;i<3;i++)
        {
            Boss head = new Boss(this,i+1);
            heads.add(head);
        }

        HUD=new playerHUD(this);

        world=new World(this);
    }
    

    public void draw() {
    	background(0);
        //println(finalRound);
        if(!playGame && endGame==false) {
            menu.display();
            menu.interact();
        }
        if(playGame) {
            play();
        }
        if(!playGame && endGame)
        {
            world.retry();
            menu.endScreen();
            menu.interact();
        }

    }    

    //This method will control all the classes for playing the game
    public void play()
    {
        if(world.level<5 && player.lives>0 && world.resetWorld==false) {
            player.move();
            HUD.display();

            //renders all game objects that are not enemies
            for (int i = 0; i < objects.size(); i++) {
                if(!(objects.get(i) instanceof Enemy))
                {
                    objects.get(i).render();
                }
            }

            for (int i = 0; i < bulletArray.size(); i++) {
                bulletArray.get(i).render();
                bulletArray.get(i).move();
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
        if(world.level>4)
        {
            finalRound=true;
        }
        //load final level
        if(finalRound)
        {
            world.finalLevel();
        }
        //displays end screen
        if(player.lives<1)
        {
            endGame=true;
            playGame=false;
        }


    }

    public void mouseReleased()
    {
        fired = true;
    }
}