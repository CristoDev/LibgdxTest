package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Shoot extends BreakElement {
    public Shoot(Vector2 position, Vector2 speed) {
        init("shoot", position, speed, Movement.MOBILE);
    }

    public Shoot(Vector2 position, float speed) {
        this(position, new Vector2(0, Math.abs(speed)));
    }
}
