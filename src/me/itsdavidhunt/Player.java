package me.itsdavidhunt;

import processing.core.*;

public class Player extends GameObject {

	private PVector movement;
	private float plHeight;
	private float plWidth;
	float jSpeed=(float).004;


	//creates player
	public Player(Main _main)
	{
		super(_main);
		plHeight= (float)(super.main.height*.03);
		plWidth= (float)(super.main.height*.02);
		pos=new PVector(super.main.width/2,super.main.height/2-plHeight);
		movement=new PVector(5,0);
	}

	//displays the player on screen
	public void render()
	{
		super.main.fill(255);
		super.main.rect(pos.x,pos.y,plWidth,-plHeight);

		addGravity(this);

		System.out.println(speed);

	}
	
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

			//if player wants to jump
			if(super.main.key=='w')
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
		pos.y -= super.main.height * jSpeed;
	}

	//controls the creation of Flare Gun and aiming
	public void flareAim()
	{
		super.main.gun=new FlareGun(main);
		super.main.gun.render();
		super.main.stroke(255);
		super.main.line(pos.x+plWidth/2, pos.y-plHeight/2, main.mouseX, main.mouseY);
	}
	  
}