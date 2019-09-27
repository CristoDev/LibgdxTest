package com.mygdx.game.breakout;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball extends BreakElement {
    protected float ballRadius=1f;

    public Ball(Vector2 position, Vector2 speed) {
        init("ballGrey", position, speed, Movement.MOBILE);
    }

    public Ball(Vector2 position) {
        init("ballGrey", position, new Vector2(MathUtils.random(-3f, 3f), MathUtils.random(2f, 5f)), Movement.MOBILE);
    }

    protected void init(String region, Vector2 position, Vector2 speed, Movement movement) {
        if (Math.abs(speed.x) < 1) {
            speed.x = Math.signum(speed.x);
        }

        ballRadius=_width/2;

        super.init(region, position, speed, movement);
    }


}
