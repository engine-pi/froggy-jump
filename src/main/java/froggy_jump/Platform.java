package froggy_jump;

import pi.actor.Rectangle;
import pi.event.CollisionEvent;
import pi.event.CollisionListener;

class Platform extends Rectangle implements CollisionListener<Frog>
{
    public Platform(double width, double height)
    {
        super(width, height);
        makeStatic();
        addCollisionListener(Frog.class, this);
        color("brown");
    }

    @Override
    public void onCollision(CollisionEvent<Frog> collisionEvent)
    {
        double frogY = collisionEvent.colliding().anchor().y();
        if (frogY < y())
        {
            collisionEvent.ignoreCollision();
            collisionEvent.colliding().jumpEnabled(false);
        }
    }

    @Override
    public void onCollisionEnd(CollisionEvent<Frog> collisionEvent)
    {
        collisionEvent.colliding().jumpEnabled(true);
    }
}
