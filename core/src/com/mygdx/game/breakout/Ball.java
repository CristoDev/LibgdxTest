package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Ball extends BreakElement {
    protected float ballRadius=1f;

    public Ball(Vector2 position, Vector2 speed) {
        init("ballGrey", position, speed, Movement.MOBILE);
        ballRadius=_width/2;
    }

}
