package me.itsdavidhunt;

import processing.core.*;

public class playerHUD {

	Main main;

	public playerHUD(Main _main)
	{
		main = _main;
	}

	//displays the player HUD
	public void display()
	{
		main.fill(255);
		main.textAlign(main.LEFT,main.CENTER);
		main.textSize((float)(main.height*.03));
		//displays current level
		main.text("Level: " + main.world.level,(float)(main.width*.01),(float)(main.height*.025));
		//displays current lives
		main.text("Lives: " + main.player.lives,(float)(main.width*.01),(float)(main.height*.05));
		//displays current flares
		main.text("Flares: " + main.clip.size(),(float)(main.width*.01),(float)(main.height*.075));
	}
}