package me.itsdavidhunt;

import processing.core.*;

 
public class Main extends PApplet {
	
	Player player;
    
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
    	player.flareAim();
    }
}