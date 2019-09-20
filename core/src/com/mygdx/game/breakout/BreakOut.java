package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BreakOut {
    private static final String ATLAS="breakout/breakout.atlas";

    private Image paddle;

    public BreakOut() {

    }

    private void init() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        paddle=new Image(atlas.findRegion("paddle_11"));
        paddle.setPosition(100, 300);
    }
}
