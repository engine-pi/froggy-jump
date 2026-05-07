package froggy_jump;

import pi.Layer;
import pi.actor.Image;
import pi.event.CollisionEvent;
import pi.event.CollisionListener;

class SpikeBall extends Image implements CollisionListener<Frog>
{
    public SpikeBall()
    {
        super("images/Spiked-Ball.png");
        pixelPerMeter(40);
        gravityScale(0);
        addCollisionListener(Frog.class, this);
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

    @Override
    public void onCollision(CollisionEvent<Frog> event)
    {
        event.colliding().kill();
    }
}
