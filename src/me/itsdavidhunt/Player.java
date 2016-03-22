package me.itsdavidhunt;

import processing.core.*;

public class Player extends GameObject {

	private PVector movement;
	private float plHeight=30;
	private float plWidth=20;


	//creates player
	public Player(Main _main)
	{
		super(_main);
		pos=new PVector(super.main.width/2,super.main.height/2-plHeight);
		movement=new PVector(5,0);
	}

	//displays the player on screen
	public void render()
	{
		super.main.fill(255);
		super.main.rect(pos.x,pos.y,plWidth,plHeight);

		addGravity(this);
	}
	
	float jump=0;
	//controls the players movements
	public void move()
	{
		if(super.main.keyPressed)
		{
			//if player wants to go right
			if(super.main.key=='d')
			{
				pos.add(movement);
			}
			
			//if player wants to go left
			if(super.main.key=='a')
			{
				pos.sub(movement);
			}
			
			if(super.main.key=='w')
			{
				inAir=true;
				while (jump <= 10) {
					jump++;
					pos.y -= jump;
				}
			}
		}
	}
	
	public void flareAim()
	{
		super.main.gun=new FlareGun(main);
		super.main.gun.render();
		super.main.stroke(255);
		super.main.line(pos.x+plHeight/2, pos.y+plWidth/2, main.mouseX, main.mouseY);
	}
	  
}