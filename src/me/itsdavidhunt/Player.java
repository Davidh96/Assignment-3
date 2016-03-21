package me.itsdavidhunt;

import processing.core.*;

public class Player extends PApplet {
	
	Main main;
	
	public PVector pos;
	private PVector movement;
	private float plHeight=30;
	private float plWidth=20;
	
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
		main.fill(255);
		main.rect(pos.x,pos.y,plWidth,plHeight);
	}
	
	float jump=0;
	int frame=0;
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
			
			if(main.key=='w')
			{
				while (jump <= 10) {
					jump++;
					pos.y -= jump;
				}
			}
		}
	}
	
	public void flareAim()
	{
		FlareGun gun=new FlareGun(pos.x,pos.y,main);
		gun.render();
		main.stroke(255);
		main.line(pos.x+plHeight/2, pos.y+plWidth/2, main.mouseX, main.mouseY);
	}
	  
}