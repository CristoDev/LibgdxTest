package com.mygdx.game.spritesheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Orc extends Character {
    public Orc() {
        TAG = this.getClass().getSimpleName();
        set_defaultSpritePath("body/male/orc.png");
    }

    protected void loadDefaultSprite()
    {
        Texture texture = getTextureAsset(_defaultSpritePath);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        _frameSprite = new Sprite(textureFrames[9][0].getTexture(), 0,0,FRAME_WIDTH, FRAME_HEIGHT);
        _currentFrame = textureFrames[9][0];
    }

    protected void loadAllAnimations(){
        Texture texture = getTextureAsset(_defaultSpritePath);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        _walkLeftFrames = new Array<TextureRegion>(9);

        for (int i = 0; i < 9; i++) {
            TextureRegion region = textureFrames[left][i];
            if( region == null ){
                Gdx.app.debug(TAG, "loadAllAnimations::Got null animation frame " + i);
            }
            _walkLeftFrames.insert(i, region);
        }

        _walkLeftAnimation = new Animation(frameDuration, _walkLeftFrames, Animation.PlayMode.LOOP);

        _currentFrame = (TextureRegion)_walkLeftAnimation.getKeyFrame(_frameTime);
    }

    public void render (SpriteBatch batch) {
        batch.draw(_currentFrame, 50, 50, FRAME_WIDTH*2, FRAME_HEIGHT*2);
    }
}

