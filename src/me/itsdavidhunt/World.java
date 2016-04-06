package me.itsdavidhunt;

import processing.core.*;
import java.util.ArrayList;

public class World {

    Main main;

    private int lanes=10;
    public int level;
    public boolean createWorld;
    public boolean resetWorld;

    public Block ground;

    public World(Main _main) {
        main = _main;
        createWorld=true;
        resetWorld=false;
        level=1;
    }

    public void generate()
    {
        if(createWorld) {
            boolean stairs[] = new boolean[lanes];
            for (int i = 0; i < stairs.length; i++) {
                stairs[i] = false;
            }

            while (main.blArray.size() < 10*level) {
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
                    if (main.blArray.size() >= 10 && main.blArray.size()<20) {
                        rand2 = main.random(5, 6);

                        if (stairs[i]) {
                            rand2 = main.random(6, 7);
                        }

                    }
                    if(main.blArray.size()>=20 && main.blArray.size()<30)
                    {
                        rand2 = main.random(3, 4);

                        if (stairs[i]) {
                            rand2 = main.random(4, 5);
                        }
                    }
                    if(main.blArray.size()>=30 && main.blArray.size()<40)
                    {
                        rand2 = main.random(1, 3);

                        if (stairs[i]) {
                            rand2 = main.random(2, 4);
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
                level++;
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
