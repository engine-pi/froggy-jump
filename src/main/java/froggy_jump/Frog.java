package froggy_jump;

import java.awt.event.KeyEvent;

import pi.Controller;
import pi.actor.Image;
import pi.event.FrameListener;
import pi.graphics.geom.Vector;

class Frog extends Image implements FrameListener
{
    private boolean jumpEnabled = true;

    private static final double MAX_SPEED = 4;

    public Frog()
    {
        super("images/Frog.png");
        pixelPerMeter(25);
        makeDynamic();
        rotationLocked(true);
    }

    public void jumpEnabled(boolean jumpEnabled)
    {
        this.jumpEnabled = jumpEnabled;
    }

    public void kill()
    {
        Controller.transitionToScene(new DeathScreen());
    }

    @Override
    public void onFrame(double pastTime)
    {
        Vector velocity = velocity();
        // A: Die Blickrichtung des Frosches steuern
        flippedHorizontally(velocity.x() < 0);
        // B: Horizontale Bewegung steuern
        if (Controller.isKeyPressed(KeyEvent.VK_A))
        {
            if (velocity.x() > 0)
            {
                velocity(new Vector(0, velocity.y()));
            }
            applyForce(Vector.LEFT.multiply(600));
        }
        else if (Controller.isKeyPressed(KeyEvent.VK_D))
        {
            if (velocity.x() < 0)
            {
                velocity(new Vector(0, velocity.y()));
            }
            applyForce(Vector.RIGHT.multiply(600));
        }
        if (Math.abs(velocity.x()) > MAX_SPEED)
        {
            velocity(new Vector(MAX_SPEED * Math.signum(velocity.x()),
                    velocity.y()));
        }
        // C: Wenn möglich den Frosch springen lassen
        if (isGrounded() && velocity.y() <= 0 && jumpEnabled)
        {
            velocity(new Vector(velocity.x(), 0));
            applyImpulse(Vector.UP.multiply(180));
        }
    }
}
