package me.itsdavidhunt;

import processing.core.*;

 
public class Player extends PApplet {
	
	Main main;
	
	PVector pos;
	PVector movement;
	
	//creates player
	public Player(Main _main)
	{
		main = _main;
		pos=new PVector(width/2,height/2);
		movement=new PVector(5,0);
	}
	
	//displays the player on screen
	public void render()
	{
		main.rect(pos.x,pos.y,20,40);
	}
	
	//controls the players movements
	public void move()
	{
		if(main.keyPressed)
		{	
			//if player wants to go right
			if(main.key=='d')
			{
				pos.add(movement);
			}
			
			//if player wants to go left
			if(main.key=='a')
			{
				pos.sub(movement);
			}
		}
	}
	  
}