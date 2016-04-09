package me.itsdavidhunt;

import processing.core.*;

public class Player extends GameObject {

	private PVector movement;
	public PVector spawnPos;
	private float plHeight;
	private float plWidth;
	private float jSpeed=(float).006;
	public int lives=3;
	private PImage img;

	//creates player
	public Player(Main _main)
	{
		super(_main);
		plHeight= (float)(main.height*.06);
		plWidth= (float)(main.width*.03);
		//plHeight= (float)(main.height*.03);
		//plWidth= (float)(main.height*.02);
		pos=new PVector(main.width/2,main.height/2-plHeight);
		movement=new PVector(5,0);
		img= main.loadImage("playerRight.png");

	}

	//displays the player on screen
	public void render()
	{
		main.image(img, pos.x, pos.y,plWidth,-plHeight);
		//main.fill(255);
		//main.rect(pos.x, pos.y,plWidth,-plHeight);

		addGravity(this);

		//if player falls off screen
		if(pos.y-getHeight()>main.height)
		{
			pos=spawnPos;
			lives--;
		}

	}

	//controls the players movements
	public void move()
	{
		if(main.keyPressed)
		{
			//if player wants to go right
			if(main.key=='d')
			{
				img= main.loadImage("playerRight.png");
				pos.add(movement);
			}

			//if player wants to go left
			if(main.key=='a')
			{
				img= main.loadImage("playerLeft.png");
				pos.sub(movement);
			}

			//if player wants to jump
			if(main.key=='w')
			{
				jump();
			}

			if(main.key=='s')
			{
				drop();
			}
		}
	}

	//returns height
	public float getHeight()
	{
		return plHeight;
	}

	//returns width
	public float getWidth()
	{
		return plWidth;
	}

	//moves player up!
	private void jump()
	{
		pos.y -= main.height * jSpeed;
	}

	//allows player to drop down through blocks
	private void drop()
	{

		if(inAir==false) {
			main.println(inAir);
			pos.y = pos.y + main.blArray.get(0).getHeight();
		}
	}

	//controls the creation of Flare Gun and aiming
	public void flareAim()
	{
		main.stroke(255);
		main.line(pos.x+plWidth/2, pos.y-plHeight/2, main.mouseX, main.mouseY);
	}

	//sets up flaregun
	public void flareGunSetup()
	{
		main.gun=new FlareGun(main);
		main.gun.render();
	}

}