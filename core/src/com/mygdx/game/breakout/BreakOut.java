package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class BreakOut {
    private static final String ATLAS="breakout/breakout.pack";

    private Sprite paddle;
    private Vector2 paddlePosition=new Vector2(100, 50);

    public BreakOut() {

    }

    public void init() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        paddle=new Sprite(atlas.findRegion("paddleBlu"));
        paddle.setPosition(paddlePosition.x, paddlePosition.y);
    }

    public void render(SpriteBatch batch) {
        paddle.draw(batch);
    }
}
