package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Shoot extends BreakElement {
    public Shoot(Vector2 position, float center, Vector2 speed) {
        init("shoot", position, speed, Movement.MOBILE);
        _sprite.setPosition(_position.x+center-_sprite.getWidth()/2, _position.y);
    }

    public Shoot(Vector2 position, float center, float speed) {
        this(position, center, new Vector2(0, Math.abs(speed)));
    }
}
