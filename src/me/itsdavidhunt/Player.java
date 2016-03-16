package me.itsdavidhunt;

import processing.core.*;

public class Player extends PApplet {
	
	Main main;
	
	PVector pos;
	PVector movement;
	int plHeight=20;
	int plWidth=10;
	
	//creates player
	public Player(Main _main)
	{
		main = _main;
		pos=new PVector(main.width/2,main.height/2);
		movement=new PVector(5,0);
	}
	
	//displays the player on screen
	public void render()
	{
		main.rect(pos.x,pos.y,plWidth,plHeight);
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
	
	public void flareAim()
	{
		main.stroke(255);
		main.line(pos.x+plHeight/2, pos.y+plWidth/2, main.mouseX, main.mouseY);
	}
	  
}