package com.mygdx.game.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
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
    protected String _name="SPELLCAST_LEFT";

    private final static String TEXTURE_ATLAS_PATH = "images/default_character.atlas";
    private static TextureAtlas TEXTURE_ATLAS = new TextureAtlas(TEXTURE_ATLAS_PATH);

    public CharacterAtlasBuilder() {
        copyBodyOrc();
        init();
    }

    public CharacterAtlasBuilder(String name, int x, int y) {
        this.x=x;
        this.y=y;
        _name=name;
        copyBody("darkelf2");
        init();
    }

    private void init() {
        //Array<TextureAtlas.AtlasRegion> regions = TEXTURE_ATLAS.findRegions(_name);
        Array<TextureAtlas.AtlasRegion> regions = new TextureAtlas(TEXTURE_ATLAS_PATH).findRegions(_name);
        _animation = new Animation(0.2f, regions, Animation.PlayMode.LOOP);
        _animation.setFrameDuration(1f/_animation.getKeyFrames().length);
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

    private void copyBody(String name) {
        // utile mais l'ecriture sur disque prend du temps...
        FileHandle hFile=Gdx.files.internal("Universal-LPC-spritesheet/body/male/"+name+".png");
        FileHandle destFile=new FileHandle("images/default_character.png");
        hFile.copyTo(destFile);
    }

    public void copyBodyLight() {
        copyBody("light");
    }

    public void copyBodyOrc() {
        copyBody("orc");
    }

}
