package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Paddle extends BreakElement {

    public Paddle(Vector2 position) {
        init("paddleBlu", position, Movement.STATIC);
    }

}
