package me.itsdavidhunt;

import processing.core.*;
import ddf.minim.*;
import java.util.ArrayList;

public class Main extends PApplet {

    Minim minim;

    AudioPlayer theme;
    AudioPlayer flareSound;

	public Player player;
    public FlareGun gun;
    public playerHUD HUD;
    public World world;
    public Menu menu;

    public ArrayList<Flare> clip=new ArrayList<Flare>();
    public ArrayList<GameObject>objects=new ArrayList<GameObject>();
    public ArrayList<Block>blArray=new ArrayList<Block>();
    public ArrayList<Boss>heads=new ArrayList<Boss>();
    public ArrayList<Bullet>bulletArray=new ArrayList<Bullet>();

    //used for when a player wants to fire a flare
    public boolean fired=false;
    //used to differentiate when a game is in menu or being playing
    public boolean playGame=false;
    //used to display end game screen
    public boolean endGame=false;
    //used to switch to final level
    public boolean finalRound=false;
    //identifies if the player has successfully finished the game
    public boolean win;

    //used to time player
    public int timer=0;

    public static void main(String[] args) {
        String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());
    }

    public void settings(){
    fullScreen();
    //size(800,500);
    }
    
    public void setup()
    {
        menu=new Menu(this);

        player=new Player(this);

        HUD=new playerHUD(this);

        world=new World(this);

        gun=new FlareGun(this);

        minim = new Minim(this);
        //load main song
        theme = minim.loadFile("main.wav", 2048);
        theme.setGain(-20);
        //load flare sound
        flareSound = minim.loadFile("flare.wav", 2048);
        theme.setGain(-30);
        //loop main song
        theme.loop();
    }
    

    public void draw() {
    	background(0);

        //if at start menu
        if(playGame==false && endGame==false ) {
            menu.display();
            menu.interact();
        }
        //if game is being played
        if(playGame) {
            timer++;
            play();
        }
        //if at end screen and game is not playing
        if(endGame && playGame==false)
        {
            world.retry();
            menu.endScreen();
            menu.interact();
        }

    }

    //This method will control all the classes for playing the game
    public void play()
    {
        if(world.level<5 && player.lives>0) {

            //renders all game objects that are not enemies
            for (int i = 0; i < objects.size(); i++) {
                if(!(objects.get(i) instanceof Enemy))
                {
                    objects.get(i).render();
                }
            }

            //create and display world
            world.generate();
/*
            if(finalRound==false) {
                //render enemies
                for (int i = 0; i < objects.size(); i++) {
                    if ((objects.get(i) instanceof Enemy)) {
                        objects.get(i).render();
                    }
                }
            }*/

            //show flare gun aimer
            player.flareAim();

            //show hud
            HUD.display();
        }
        //ifplayer has reached final level
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
            win=false;
            endGame=true;
            playGame=false;
        }

    }

    public void mouseReleased()
    {
            fired = true;
    }
}