package com.mygdx.game.breakout;

import com.badlogic.gdx.math.Vector2;

public class Brick extends BreakElement {
    private String[] colors={"blue", "green", "red", "purple", "grey", "yellow"};
    private String _color=null;

    public Brick(String color, Vector2 position) {
        _color=color;

        init("element_"+_color+"_rectangle", position, Movement.STATIC);
    }


}
