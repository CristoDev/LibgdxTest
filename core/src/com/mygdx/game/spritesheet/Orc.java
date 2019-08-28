package com.mygdx.game.spritesheet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Orc extends Character {
    public Orc() {
        TAG = this.getClass().getSimpleName();
        set_defaultSpritePath("body/male/orc.png");
    }

    public void render (SpriteBatch batch) {
        batch.draw(_currentFrame, 50, 50, FRAME_WIDTH*2, FRAME_HEIGHT*2);
    }
}

