package froggy_jump;

import pi.actor.Rectangle;

class Platform extends Rectangle
{
    public Platform(double width, double height)
    {
        super(width, height);
        makeStatic();
        color("brown");
    }
}
