package froggy_jump;

import pi.Camera;
import pi.Controller;
import pi.Random;
import pi.Scene;
import pi.graphics.geom.Vector;

public class FroggyJump extends Scene
{
    private Frog frog;

    private static final double PLATFORM_HEIGHT = 0.5;

    public FroggyJump()
    {
        frog = new Frog();
        add(frog);
        gravityOfEarth();
        Camera camera = camera();
        camera.focus(frog);
        camera.offset(new Vector(0, 4));
        makeLevel(40);
        makePlatforms(10);
    }

    private void makePlatforms(int heightLevel)
    {
        for (int i = 0; i < heightLevel; i++)
        {
            Platform platform = new Platform(5, PLATFORM_HEIGHT);
            platform.anchor(0, (double) i * 4);
            add(platform);
        }
    }

    private void makeLevel(int heightLevel)
    {
        for (int i = 0; i < heightLevel; i++)
        {
            int numPlatforms = Random.range(2) + 1;
            for (int j = 0; j < numPlatforms; j++)
            {
                Platform platform = new Platform((double) 6 / numPlatforms,
                        PLATFORM_HEIGHT);
                platform.anchor(numPlatforms * (j + 1) * i * Random.range(),
                    (double) i * 4);
                add(platform);
            }
            if (i > 3)
            {
                for (int j = 0; j < Random.range(3); j++)
                {
                    SpikeBall.setupSpikeBall(Random.range() * (4 + j) * i,
                        Random.range() * 4 + 0.5 + 5 * i,
                        layer());
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Controller.instantMode(false);
        Controller.start(new FroggyJump(), 400, 600);
    }
}
