package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

public class BreakElement {
    protected static final String ATLAS="breakout/breakout.pack";
    protected static TextureAtlas atlas=new TextureAtlas(Gdx.files.internal(ATLAS));
    protected Vector2 _position=new Vector2(0,0), _speed=new Vector2(0,0);
    protected float _width=1, _height=1;
    protected Sprite _sprite=null;
    protected Rectangle _boundingBox=new Rectangle(), _boundingBoxX=new Rectangle(), _boundingBoxY=new Rectangle();
    protected Movement _movement=Movement.STATIC;

    public enum Movement {
        STATIC,
        MOBILE;
    }

    public BreakElement() {

    }

    protected void init(String region, Vector2 position, Vector2 speed, Movement movement) {
        TextureRegion texture=atlas.findRegion(region);
        _width=texture.getRegionWidth();
        _height=texture.getRegionHeight();

        _position=position;
        _speed=speed;
        _movement=movement;

        _sprite=new Sprite(texture);
        _sprite.setPosition(_position.x, _position.y);

        initBoundingBoxes();
        Tools.debug("width: "+_width);
    }

    protected void init(String region, Vector2 position, Movement movement) {
        init(region, position, new Vector2(0, 0), movement);
    }

    private void initBoundingBoxes() {
        _boundingBox.set(_position.x, _position.y, _width, _height);

        if (_movement.equals(Movement.MOBILE)) {
            _boundingBoxX.set(_position.x+_speed.x, _position.y, _width, _height);
            _boundingBoxY.set(_position.x, _position.y+_speed.y, _width, _height);
        }
        else {
            _boundingBoxX=_boundingBox;
            _boundingBoxY=_boundingBox;
        }
    }

    public float getX() {
        return _sprite.getX();
    }

    public float getY() {
        return _sprite.getY();
    }

    public void setSpeedCollisionX() {
        _speed.x*=-1;
    }

    public void setSpeedCollisionY() {
        _speed.y*=-1;
    }

    public Rectangle getBoundingBox() {
        return _boundingBox;
    }

    public void updateBoundingBox() {
        _boundingBox.set(_sprite.getX(), _sprite.getY(), _width, _height);
        _boundingBoxX.set(_sprite.getX()+_speed.x, _sprite.getY(), _width, _height);
        _boundingBoxY.set(_sprite.getX(), _sprite.getY()+_speed.y, _width, _height);
    }

    // @TODO refaire le calcul pour partir du milieu du rectangle
    public void setBoundingBoxSize(float width, float height) {
        _boundingBox.setSize(width, height);
    }

    public void setPositionBySpeed() {
        _sprite.setPosition(_sprite.getX()+_speed.x, _sprite.getY()+_speed.y);
    }



    public boolean wallCollision(float windowWidth, float windowHeight) {
        boolean result=false;
        if (_sprite.getX() < 0 || _sprite.getX() + _width > windowWidth) {
            setSpeedCollisionX();
            result=true;
        }

        if (_sprite.getY() < 0 || _sprite.getY() + _height > windowHeight) {
            setSpeedCollisionY();
            result=true;
        }

        return result;
    }


    public boolean elementCollision(Rectangle rectangle) {
        boolean result=false;

        if (_boundingBoxX.overlaps(rectangle)) {
            setSpeedCollisionX();
            Tools.debug("collision sur X");
            result=true;
        }

        if (_boundingBoxY.overlaps(rectangle)) {
            setSpeedCollisionY();
            Tools.debug("collision sur Y");
            result=true;
        }

        return result;
    }


    public void render(SpriteBatch batch) {
        _sprite.draw(batch);
    }

}
