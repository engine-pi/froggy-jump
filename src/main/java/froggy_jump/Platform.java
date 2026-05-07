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
        color("brown");
        addCollisionListener(Frog.class, this);

    }

    @Override
    public void onCollision(CollisionEvent<Frog> event)
    {
        Frog frog = event.colliding();
        if (frog.y() < y())
        {
            event.ignoreCollision();
            frog.jumpEnabled(false);
        }
    }

    @Override
    public void onCollisionEnd(CollisionEvent<Frog> event)
    {
        event.colliding().jumpEnabled(true);
    }
}
