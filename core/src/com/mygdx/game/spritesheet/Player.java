package com.mygdx.game.spritesheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Player extends Character {
    /*
    public Animation walk;
    public static final AssetManager _assetManager = new AssetManager();
    private static InternalFileHandleResolver _filePathResolver =  new InternalFileHandleResolver();
    private static final String _defaultSpritePath = "images/light.png";
    int left=9, up=8, down=10, right=11;

    private Animation _walkLeftAnimation;
    private Array<TextureRegion> _walkLeftFrames;

    protected float _frameTime = 0f;
    protected float frameDuration=0.07f;
    protected Sprite _frameSprite = null;
    protected TextureRegion _currentFrame = null;

    public final int FRAME_WIDTH = 64;
    public final int FRAME_HEIGHT = 64;
*/

    public Player() {
        TAG = this.getClass().getSimpleName();
    }

    public static Texture getTextureAsset(String textureFilenamePath) {
        Texture texture=null;

        if (_assetManager.isLoaded(textureFilenamePath)) {
            texture= _assetManager.get(textureFilenamePath, Texture.class);
        }
        else {
            Gdx.app.debug(TAG, "getTextureAsset::Texture is not loaded "+ textureFilenamePath);
        }

        return texture;
    }

    protected void loadDefaultSprite()
    {
        Texture texture = getTextureAsset(_defaultSpritePath);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        _frameSprite = new Sprite(textureFrames[9][0].getTexture(), 0,0,FRAME_WIDTH, FRAME_HEIGHT);
        _currentFrame = textureFrames[9][0];
    }

    protected void loadAllAnimations(){
        Pixmap player=new Pixmap(Gdx.files.internal("images/light.png"));
        // pour l'ordre utiliser linkedHashMap
        player.drawPixmap(new Pixmap(Gdx.files.internal("images/mail_male.png")), 0, 0);
        player.drawPixmap(new Pixmap(Gdx.files.internal("images/jacket_male.png")), 0, 0);
        player.drawPixmap(new Pixmap(Gdx.files.internal("images/light-blonde.png")), 0, 0);

        Texture texture = new Texture(player);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        _walkLeftFrames = new Array<TextureRegion>(9);

        for (int i = 0; i < 9; i++) {
            TextureRegion region = textureFrames[right][i];
            if( region == null ){
                Gdx.app.debug(TAG, "loadAllAnimations::Got null animation frame " + i);
            }
            _walkLeftFrames.insert(i, region);
        }

        _walkLeftAnimation = new Animation(0.11f, _walkLeftFrames, Animation.PlayMode.LOOP);

        _currentFrame = (TextureRegion)_walkLeftAnimation.getKeyFrame(_frameTime);
    }


    public void render (SpriteBatch batch) {
        batch.draw(_currentFrame, 200, 50, FRAME_WIDTH*2, FRAME_HEIGHT*2);
    }
}

