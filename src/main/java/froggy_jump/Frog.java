package froggy_jump;

import java.awt.event.KeyEvent;

import pi.Controller;
import pi.actor.Image;
import pi.event.FrameListener;

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
        // Die Blickrichtung des Frosches steuern
        flippedHorizontally(velocityX() < 0);

        // Die horizontale Bewegung steuern
        if (Controller.isKeyPressed(KeyEvent.VK_A))
        {
            if (velocityX() > 0)
            {
                velocityX(0);
            }
            applyForce(-600, 0);
        }
        else if (Controller.isKeyPressed(KeyEvent.VK_D))
        {
            if (velocityX() < 0)
            {
                velocityX(0);
            }
            applyForce(600, 0);
        }

        // Die horizontale Geschwindigkeit begrenzen
        if (Math.abs(velocityX()) > MAX_SPEED)
        {
            velocityX(MAX_SPEED * Math.signum(velocityX()));
        }

        // Wenn möglich den Frosch springen lassen
        if (isGrounded() && velocityY() <= 0 && jumpEnabled)
        {
            velocityY(0);
            applyImpulse(0, 180);
        }
    }
}
