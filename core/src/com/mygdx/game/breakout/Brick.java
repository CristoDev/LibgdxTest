package com.mygdx.game.breakout;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Brick extends BreakElement {
    private String[] colors={"blue", "green", "red", "purple", "grey", "yellow"};
    private String _color=null;
    private float bonus=15f;

    public Brick(String color, Vector2 position, int health) {
        _color = color;
        _health=health;

        init("element_" + _color + "_rectangle", position, Movement.STATIC);
    }

    public Brick(Vector2 position, int health) {
        _color=colors[MathUtils.random(colors.length-1)];
        _health=health;

        init("element_"+_color+"_rectangle", position, Movement.STATIC);

    }

    public Brick(String color, Vector2 position) {
        this(color, position, 1);
    }

    public void setBonus(float value) {
        bonus=value;
    }

    public boolean showBonus() {
        return (MathUtils.random(100) < bonus);
    }

    public String getColor() {
        return _color;
    }


}
