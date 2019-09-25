package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Paddle extends BreakElement {

    // @todo modifier les tests de bounding bxo si la balle est sous le paddle
    public Paddle(Vector2 position) {
        _height=1;
        init("paddleBlu", position, Movement.STATIC);
    }

}
