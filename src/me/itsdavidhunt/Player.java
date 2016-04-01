package me.itsdavidhunt;

import processing.core.*;

public class Player extends GameObject {

	private PVector movement;
	private float plHeight;
	private float plWidth;
	private float jSpeed=(float).004;
	public int lives=3;


	//creates player
	public Player(Main _main)
	{
		super(_main);
		plHeight= (float)(main.height*.03);
		plWidth= (float)(main.height*.02);
		pos=new PVector(main.width/2,main.height/2-plHeight);
		movement=new PVector(5,0);
	}

	//displays the player on screen
	public void render()
	{
		main.fill(255);
		main.rect(pos.x,pos.y,plWidth,-plHeight);

		addGravity(this);

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

			//if player wants to jump
			if(main.key=='w')
			{
				jump();
			}
		}
	}

	public float getHeight()
	{
		return plHeight;
	}

	public float getWidth()
	{
		return plWidth;
	}

	//moves player up!
	private void jump()
	{
		pos.y -= main.height * jSpeed;
	}

	//controls the creation of Flare Gun and aiming
	public void flareAim()
	{
		main.gun=new FlareGun(main);
		main.gun.render();
		main.stroke(255);
		main.line(pos.x+plWidth/2, pos.y-plHeight/2, main.mouseX, main.mouseY);
	}
	  
}