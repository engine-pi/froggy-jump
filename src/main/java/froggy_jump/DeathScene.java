package froggy_jump;

import java.awt.event.KeyEvent;

import pi.Controller;
import pi.Scene;
import pi.actor.Text;
import pi.event.KeyStrokeListener;

class DeathScene extends Scene implements KeyStrokeListener
{
    public DeathScene()
    {
        Text message = new Text("You Died. Press any button to try again");
        message.height(.6);
        message.center(camera().focus());
        add(message);
    }

    @Override
    public void onKeyDown(KeyEvent e)
    {
        Controller.transitionToScene(new FroggyJump());
    }
}
