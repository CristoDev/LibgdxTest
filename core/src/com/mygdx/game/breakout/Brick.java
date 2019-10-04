package com.mygdx.game.breakout;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

// @TODO creer une factory pour chaque type de brique (couleur, points, bonus)
public class Brick extends BreakElement {
    private String[] _colors={"blue", "green", "red", "purple", "grey", "yellow"};
    private String _color=null;
    private float _bonus=15f;

    public Brick(String color, Vector2 position, int health) {
        _color = color;
        _health=health;

        init("element_" + _color + "_rectangle", position, Movement.STATIC);
    }

    public Brick(int colorIndex, Vector2 position, int health) {
        _color = _colors[colorIndex];
        _health=health;

        init("element_" + _color + "_rectangle", position, Movement.STATIC);
    }

    public Brick(String color, Vector2 position, int health, float bonus) {
        _color = color;
        _health=health;
        _bonus=bonus;

        init("element_" + _color + "_rectangle", position, Movement.STATIC);
    }

    public Brick(Vector2 position, int health) {
        _color=_colors[MathUtils.random(_colors.length-1)];
        _health=health;

        init("element_"+_color+"_rectangle", position, Movement.STATIC);

    }

    public Brick(String color, Vector2 position) {
        this(color, position, 1);
    }

    public void setBonus(float value) {
        _bonus=value;
    }

    public boolean showBonus() {
        return (MathUtils.random(100) < _bonus);
    }

    public String getColor() {
        return _color;
    }


}
