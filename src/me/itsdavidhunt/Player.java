package me.itsdavidhunt;

import processing.core.*;

 
public class Player extends PApplet {
	
	Main main;
	
	PVector pos;
	
	public Player(Main _main)
	{
		main = _main;
	}
	
	public void render()
	{
		pos=new PVector(width/2,height/2);
		main.rect(pos.x,pos.y,20,40);
	}
	
	public void move()
	{
		
	}
}