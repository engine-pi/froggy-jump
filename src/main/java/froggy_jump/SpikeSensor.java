package froggy_jump;

import pi.Rectangle;
import pi.event.CollisionEvent;
import pi.event.CollisionListener;

class SpikeSensor extends Rectangle implements CollisionListener<Frog>
{
    private SpikeBall ball;

    public SpikeSensor(SpikeBall ball)
    {
        super(2, 8);
        this.ball = ball;
        visible(false);
        makeSensor();
        addCollisionListener(Frog.class, this);
        gravityScale(0);
    }

    @Override
    public void onCollision(CollisionEvent<Frog> event)
    {
        ball.gravityScale(1);
    }
}
