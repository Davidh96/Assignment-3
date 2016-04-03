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
        if(main.blArray.size()<20) {
            float laneWidth = main.width / lanes;
            float laneHeight = main.height / lanes;


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
            }
        }
        for(int i=0;i<main.blArray.size();i++)
        {
            main.blArray.get(i).render();
        }
    }
}
