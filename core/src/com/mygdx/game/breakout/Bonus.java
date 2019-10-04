package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Bonus extends BreakElement {

    public Bonus(String color, Vector2 position, Vector2 speed) {
        init("element_"+color+"_diamond", position, speed, Movement.MOBILE);
    }

    public Bonus(String color, Vector2 position, float speed) {
        this(color, position, new Vector2(0, -Math.abs(speed)));
    }
}
