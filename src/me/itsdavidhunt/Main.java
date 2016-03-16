package me.itsdavidhunt;

import processing.core.*;

 
public class Main extends PApplet {
	
	Player player;
    
    public static void main(String[] args) {
        String[] a = {"MAIN"};
        PApplet.runSketch( a, new Main());
    }

    public void settings(){
        fullScreen(); 
    }
    
    public void setup()
    {
    	background(0);
    	player=new Player(this);
    }
    

    public void draw() {
        player.render();
    }    
}