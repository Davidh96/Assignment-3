package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class World {

    Main main;

    private int lanes=10;
    public int level;
    public boolean createWorld;
    public boolean resetWorld;
    public boolean initial;
    private int randEnemySpawn[] = new int[2];
    private int randfPickupSpawn[] = new int[2];
    private int randhPickupSpawn;
    private int num=0;

    public Block ground;

    public World(Main _main) {
        main = _main;
        createWorld=true;
        resetWorld=false;
        level=1;
    }

    //randomly generates world
    public void generate()
    {
        //if new world needs to be created
        if(createWorld) {

            //gives player 5 flares each level
            for (int i = 0; i < 5; i++) {
                Flare flare = new Flare(main);
                main.clip.add(flare);
            }

            //will ensure that the player can go up onto higher platforms
            boolean stairs[] = new boolean[lanes];
            for (int i = 0; i < stairs.length; i++) {
                stairs[i] = false;
            }

            //every level will add an extra 10 blocks
            while (main.blArray.size() < 10*level) {
                float laneWidth = main.width / lanes;
                float laneHeight = main.height / lanes;

                //give the two enemies per horizontal lane random spawn location
                randEnemySpawn[num]=(int)main.random(1, lanes);
                randEnemySpawn[num+1]=(int)main.random(1, lanes);
                //if enemy spawn on same location, move them
                while(randEnemySpawn[num+1]==randEnemySpawn[num])
                {
                    randEnemySpawn[num+1]=(int)main.random(1, lanes);
                }

                //give the two flares per horizontal lane random spawn location
                randfPickupSpawn[num]=(int)main.random(1, lanes);
                randfPickupSpawn[num+1]=(int)main.random(1, lanes);
                //if flare spawn on same location, move them
                while(randfPickupSpawn[num+1]==randfPickupSpawn[num])
                {
                    randfPickupSpawn[num+1]=(int)main.random(1, lanes);
                }

                //give the health pickup per horizontal lane random spawn location
                randhPickupSpawn=(int)main.random(1, lanes);
                //ensures that the health pickup and flare pickup dont spawn on same block
                while(randhPickupSpawn==randfPickupSpawn[num] || randhPickupSpawn==randfPickupSpawn[num+1])
                {
                    randhPickupSpawn=(int)main.random(1, lanes);
                }

                //go through each vertical lane
                for (int i = 0; i < lanes; i++) {

                    //gives the location of a block in the horzintal lane
                    float rand2 = 0;

                    //if level is level 1
                    if (main.blArray.size() < 10) {
                        rand2 = main.random(7, 9);

                        //if very first block
                        if(i==0)
                        {
                            //spawn player
                            main.player.pos=new PVector( main.player.getWidth(),rand2 * laneHeight);
                            main.objects.add(main.player);
                        }
                        //makes any above block in same lane closer to this block
                        if (rand2 > 8) {
                            stairs[i] = true;
                        }

                    }
                    //if level is level 2
                    if (main.blArray.size() >= 10 && main.blArray.size()<20) {
                        rand2 = main.random(5, 6);

                        //makes block closer to the block below it
                        if (stairs[i]) {
                            rand2 = main.random(6, 7);
                        }

                    }

                    //if level is level 3
                    if(main.blArray.size()>=20 && main.blArray.size()<30)
                    {
                        rand2 = main.random(3,4);

                        //makes block closer to the block below it
                        if (stairs[i]) {
                            rand2 = main.random(4, 5);
                        }
                    }

                    //if level is level 4
                    if(main.blArray.size()>=30 && main.blArray.size()<40)
                    {
                        rand2 = main.random(1, 2);

                        //makes block closer to the block below it
                        if (stairs[i]) {
                            rand2 = main.random(2, 3);
                        }
                    }

                    //give the block its position
                    float xPos = i * laneWidth;
                    float yPos = rand2 * laneHeight;

                    //create block
                    ground = new Block(main);
                    ground.lane = i;
                    ground.pos = new PVector(xPos, yPos);
                    main.blArray.add(ground);

                    //if the enemy random lane number is the current blocks lane
                    if (randEnemySpawn[num]== i || randEnemySpawn[num+1] == i) {
                        Enemy enemy = new Enemy(main);
                        enemy.pos = new PVector(xPos + (ground.getWidth() / 2), yPos - enemy.getWidth());
                        main.objects.add(enemy);
                    }

                    //if the pickup random lane number is the current blocks lane
                    if (randfPickupSpawn[num] == i || randfPickupSpawn[num+1] == i) {
                        FlarePickup flare = new FlarePickup(main);
                        flare.pos = new PVector(xPos + ground.getWidth() / 2, yPos - flare.getWidth());
                        main.objects.add(flare);

                    }

                    //if the pickup random lane number is the current blocks lane
                    if (randhPickupSpawn== i) {
                        HealthPickup health = new HealthPickup(main);
                        health.pos = new PVector(xPos + ground.getWidth() / 2, yPos - health.getWidth());
                        main.objects.add(health);

                    }
                }
            }

            //the world has now been created
            createWorld=false;
        }

        //sets player spawn location
        main.player.spawnPos=new PVector(main.blArray.get(0).pos.x,main.blArray.get(0).pos.y);

        if(main.finalRound==false) {

            //used to hold index of highest block
            int highestBlock = 5;

            //get the highest block index
            for (int i = 5; i < main.blArray.size(); i++) {
                //if current highest is smaller than this other block, update highest
                if (main.blArray.get(i).pos.y < main.blArray.get(highestBlock).pos.y) {
                    highestBlock = i;
                }
            }


            //creates exit when there are more than 9 block created
            //prevents game from crashing when resetting world
            if (main.blArray.size() > 9) {
                Exit exit = new Exit(main);
                exit.pos = new PVector(main.blArray.get(highestBlock).pos.x + (main.blArray.get(highestBlock).getWidth()), main.blArray.get(highestBlock).pos.y);
                exit.render();
                exit.detect();
            }
        }

        //render all blocks
        for(int i=0;i<main.blArray.size();i++)
        {
            main.blArray.get(i).render();
        }

        //if the world needs to be reset(due to player progressing to new level)
        if(resetWorld)
        {
            reset();
        }

    }

    //creates final level
    public void finalLevel()
    {
        //sets the level to look like level 1
        level=1;

        //initial create of heads
        if(initial) {
            for (int i = 0; i < 3; i++) {
                Boss head = new Boss(main, i + 1);
                main.heads.add(head);
            }
            initial=false;
        }

        //if heads have been destroyed
        if(main.heads.size()==0)
        {
            main.endGame=true;
            main.finalRound=false;
            main.playGame=false;
        }

        //display heads
        for (int i = 0; i < main.heads.size(); i++) {
            main.heads.get(i).render();
        }


    }

    //sets up the game for when a player wants to retry
    public void retry()
    {
        //initialise the level and health
        level=1;
        main.player.lives=3;
        reset();
    }

    //removes objects
    private void reset()
    {
        //remove all blocks
        for(int i=0;i<main.blArray.size();i++)
        {
            main.blArray.remove(i);
        }
        //remove all objects(player,flares,enemies etc..)
        for(int i=0;i<main.objects.size();i++)
        {
            main.objects.remove(i);
        }

        for(int i=0;i<main.clip.size();i++)
        {
            main.clip.remove(i);
        }

        //once all has been removed
        if(main.blArray.size()<1 && main.objects.size()<1 )
        {
            //create new world
            createWorld=true;
            resetWorld=false;
        }
    }
}
