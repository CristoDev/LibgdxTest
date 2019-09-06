package com.mygdx.game.images;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Tools;

public class CharacterAtlasBuilder {
    private int x=500, y=100;
    private static final String TAG = CharacterAtlasBuilder.class.getSimpleName();
    private Animation _animation;
    protected float _frameTime = 0f;
    protected TextureRegion _currentFrame;
    protected String _name="ORC_SPELLCAST_LEFT";

    private final static String TEXTURE_ATLAS_PATH = "images/default_character.atlas";
    private static TextureAtlas TEXTURE_ATLAS = new TextureAtlas(TEXTURE_ATLAS_PATH);

    public CharacterAtlasBuilder() {
        init();
    }

    public CharacterAtlasBuilder(String name, int x, int y) {
        this.x=x;
        this.y=y;
        _name=name;
        init();
    }

    private void init() {
        Array<TextureAtlas.AtlasRegion> regions = TEXTURE_ATLAS.findRegions(_name);
        _animation = new Animation(0.1f, regions, Animation.PlayMode.LOOP);
        Tools.debug(TAG, "Nombre de frames: "+_animation.getKeyFrames().length);
    }

    public void update(float delta) {
        _frameTime = (_frameTime + delta)%5;
        _currentFrame=getCurrentFrame(_frameTime);
    }

    public TextureRegion getCurrentFrame(float _frameTime) {
        return (TextureRegion)_animation.getKeyFrame(_frameTime);
    }

    public void render(SpriteBatch batch) {
        batch.draw(_currentFrame, x,y, 64, 64);
    }

}
