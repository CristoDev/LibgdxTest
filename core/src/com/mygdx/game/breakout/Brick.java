package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Brick {
    private String[] colors={"blue", "green", "red", "purple", "grey", "yellow"};
    private static final String ATLAS="breakout/breakout.pack";
    private Sprite _brick;
    private static TextureAtlas atlas=null;
    private String _color;
    private Vector2 _position;
    private Rectangle _boundingBox=new Rectangle();

    public Brick(String color, Vector2 position) {
        _color=color;
        _position=position;

        init();
    }

    private void init() {
        if (atlas == null) {
            atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        }

        TextureRegion texture=atlas.findRegion("element_"+_color+"_rectangle");
        _brick=new Sprite(texture);
        _brick.setPosition(_position.x, _position.y);
        _boundingBox.set(_position.x, _position.y, texture.getRegionWidth(), texture.getRegionHeight());
    }

    public void render(SpriteBatch batch) {
        _brick.draw(batch);
    }

    public Rectangle getBoundingBox() {
        return _boundingBox;
    }
}
