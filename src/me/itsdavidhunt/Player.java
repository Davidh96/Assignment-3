package me.itsdavidhunt;

import processing.core.*;

public class Player extends GameObject {

	 PVector movement;
 PVector spawnPos;
 float plHeight;
 float plWidth;
 float jSpeed=(float).006;
 int lives=3;
	private PImage img;
	//creates player
	public Player(Main _main)
	{
		super(_main);
		plHeight= (float)(main.height*.06);
		plWidth= (float)(main.width*.03);
		pos=new PVector(main.width/2,main.height/2-plHeight);
		img=main.loadImage("PlayerRight.png");
		movement=new PVector(5,0);

	}

	//displays the player on screen
	public void render()
	{
		//render image
		main.image(img, pos.x, pos.y, plWidth, -plHeight);

		//if player falls off screen
		if(pos.y-getHeight()>main.height)
		{
			pos=spawnPos;
			lives--;
		}

		//allow player to move
		move();

		//object is now affected by gravity
		addGravity();

		//display gun
		flareGunSetup();

	}

	//controls the players movements
	public void move()
	{
		if(main.keyPressed)
		{
			//if player wants to go right
			if(main.key=='d')
			{
				img=main.loadImage("PlayerRight.png");
				//switch the side the flare gun appears on
				main.gun.img=main.loadImage("GunRight.png");
				main.gun.switchSide=0;
				pos.add(movement);
			}

			//if player wants to go left
			if(main.key=='a')
			{
				img=main.loadImage("PlayerLeft.png");
				//switch the side the flare gun appears on
				main.gun.img=main.loadImage("GunLeft.png");
				main.gun.switchSide=-getWidth()/2;
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
		//prevents player from dropping through blocks at high speed
		if(inAir==false) {
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
		main.gun.render();
	}

}