package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class World {

    Main main;

    private int lanes=10;
    public boolean createWorld;
    public boolean resetWorld;

    public Block ground;

    public World(Main _main) {
        main = _main;
        createWorld=true;
        resetWorld=false;
    }

    public void generate()
    {
        if(createWorld) {
            boolean stairs[] = new boolean[lanes];
            for (int i = 0; i < stairs.length; i++) {
                stairs[i] = false;
            }

            while (main.blArray.size() < 20) {
                float laneWidth = main.width / lanes;
                float laneHeight = main.height / lanes;

                int randEnemySpawn = (int) main.random(0, lanes);
                int randPickupSpawn = (int) main.random(0, lanes);

                for (int i = 0; i < lanes; i++) {

                    float rand2 = 0;

                    if (main.blArray.size() < 10) {
                        rand2 = main.random(7, 9);

                        if (rand2 > 8) {
                            stairs[i] = true;
                        }


                    }
                    if (main.blArray.size() >= 10) {
                        rand2 = main.random(5, 6);

                        if (stairs[i]) {
                            rand2 = main.random(6, 7);
                        }

                    }


                    float xPos = i * laneWidth;
                    float yPos = rand2 * laneHeight;

                    ground = new Block(main);
                    ground.lane = i;
                    ground.pos = new PVector(xPos, yPos);
                    main.blArray.add(ground);

                    if (randEnemySpawn == i) {
                        Enemy enemy = new Enemy(main);
                        enemy.pos = new PVector(xPos + ground.getWidth() / 2, yPos - enemy.getWidth());
                        main.objects.add(enemy);
                    }

                    if (randPickupSpawn == i) {
                        FlarePickup flare = new FlarePickup(main);
                        flare.pos = new PVector(xPos + ground.getWidth() / 2, yPos - flare.getWidth());
                        main.objects.add(flare);

                    }
                }
            }

            createWorld=false;
        }
        if(resetWorld)
        {
            for(int i=0;i<main.blArray.size();i++)
            {
                main.blArray.remove(i);
            }
            if(main.blArray.size()<1)
            {
                createWorld=true;
                resetWorld=false;
            }
        }

        int highestBlock=0;

        for(int i=0;i<main.blArray.size();i++)
        {
            main.blArray.get(i).render();
            if(main.blArray.get(i).pos.y<main.blArray.get(highestBlock).pos.y)
            {
                highestBlock=i;
            }
        }

        if(main.blArray.size()>9) {
            Exit exit = new Exit(main);
            exit.pos = new PVector(main.blArray.get(highestBlock).pos.x + (main.blArray.get(highestBlock).getWidth()), main.blArray.get(highestBlock).pos.y);
            exit.render();
            exit.detect();
        }

    }
}
