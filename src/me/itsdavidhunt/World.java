package me.itsdavidhunt;

import processing.core.*;

public class World {

    Main main;

    private int lanes=10;

    public Block ground;

    public World(Main _main) {
        main = _main;
    }

    public void generate()
    {
        if(main.blArray.size()<10) {
            float laneWidth = main.width / lanes;
            float laneHeight = main.height / lanes;

            int randEnemySpawn=(int)main.random(0,lanes);
            int randPickupSpawn=(int)main.random(0,lanes);


            for (int i = 0; i < lanes; i++) {

                float rand2;

                if(main.blArray.size()<10) {
                    rand2 = main.random(7, 9);
                }
                else
                {
                    rand2 = main.random(4, 6);
                }

                float xPos = i * laneWidth;
                float yPos = rand2 * laneHeight;

                ground = new Block(main);
                ground.lane=i;
                ground.pos = new PVector(xPos, yPos);
                main.blArray.add(ground);

                if(randEnemySpawn==i)
                {
                    Enemy enemy=new Enemy(main);
                    enemy.pos=new PVector(xPos+ground.getWidth()/2,yPos-enemy.getWidth());
                    main.objects.add(enemy);
                }

                if(randPickupSpawn==i)
                {
                    FlarePickup flare=new FlarePickup(main);
                    flare.pos=new PVector(xPos+ground.getWidth()/2,yPos-flare.getWidth());
                    main.objects.add(flare);

                }
            }
        }
        for(int i=0;i<main.blArray.size();i++)
        {
            main.blArray.get(i).render();
        }
    }
}
