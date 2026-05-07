
package froggy_jump;

import pi.Layer;
import pi.actor.Image;

class SpikeBall extends Image
{
    public SpikeBall()
    {
        super("images/Spiked-Ball.png");
        pixelPerMeter(40);
        gravityScale(0);
    }

    public static SpikeBall create(double x, double y, Layer layer)
    {
        SpikeBall ball = new SpikeBall();
        ball.center(x, y);
        SpikeSensor sensor = new SpikeSensor(ball);
        sensor.anchor(x - 1, y - 8);
        layer.add(ball, sensor);
        return ball;
    }
}
