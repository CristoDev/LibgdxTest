package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Paddle extends BreakElement {
    private Vector2 speed0=new Vector2(0, 0);

    // @todo prise en compte du clavier
    public Paddle(Vector2 position) {
        _height=1;
        init("paddleBlu", position, speed0, Movement.MOBILE);
    }

}
