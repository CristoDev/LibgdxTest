package com.mygdx.game.images;

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

public class Player {
    public Animation walk;
    public static final AssetManager _assetManager = new AssetManager();
    private static InternalFileHandleResolver _filePathResolver =  new InternalFileHandleResolver();
    private static final String _defaultSpritePath = "images/orc.png";
    private static final String TAG = Player.class.getSimpleName();

    private Animation _walkLeftAnimation;
    private Array<TextureRegion> _walkLeftFrames;

    protected float _frameTime = 0f;
    protected Sprite _frameSprite = null;
    protected TextureRegion _currentFrame = null;

    public final int FRAME_WIDTH = 64;
    public final int FRAME_HEIGHT = 64;


    public Player() {
    }

    public void init() {
        // charger le fichier d'images
        loadTextureAsset(_defaultSpritePath);
        loadDefaultSprite();
        loadAllAnimations();
    }

    public static void loadTextureAsset(String textureFilenamePath){
        if( textureFilenamePath == null || textureFilenamePath.isEmpty() ){
            return;
        }

        if( _assetManager.isLoaded(textureFilenamePath) ){
            return;
        }

        //load asset
        if( _filePathResolver.resolve(textureFilenamePath).exists() ){
            _assetManager.setLoader(Texture.class, new TextureLoader(_filePathResolver));
            _assetManager.load(textureFilenamePath, Texture.class);
            //Until we add loading screen, just block until we load the map
            _assetManager.finishLoadingAsset(textureFilenamePath);
        }
        else{
            Gdx.app.debug(TAG, "loadTextureAsset::Texture doesn't exist!: " + textureFilenamePath );
        }
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

    private void loadDefaultSprite()
    {
        Texture texture = getTextureAsset(_defaultSpritePath);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        _frameSprite = new Sprite(textureFrames[9][0].getTexture(), 0,0,FRAME_WIDTH, FRAME_HEIGHT);
        _currentFrame = textureFrames[9][0];
    }

    private void loadAllAnimations(){
        //Walking animation
        Texture texture = getTextureAsset(_defaultSpritePath);
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        _walkLeftFrames = new Array<TextureRegion>(9);

        for (int i = 0; i < 9; i++) {
            TextureRegion region = textureFrames[9][i];
            if( region == null ){
                Gdx.app.debug(TAG, "loadAllAnimations::Got null animation frame " + i);
            }
            _walkLeftFrames.insert(i, region);
        }

        _walkLeftAnimation = new Animation(0.11f, _walkLeftFrames, Animation.PlayMode.LOOP);

        _currentFrame = (TextureRegion)_walkLeftAnimation.getKeyFrame(_frameTime);
    }



    public void update(float delta){
        _frameTime = (_frameTime + delta)%5; //Want to avoid overflow
        _currentFrame=(TextureRegion)_walkLeftAnimation.getKeyFrame(_frameTime);
        //Gdx.app.debug(TAG, "Player::update "+_walkLeftAnimation.getKeyFrameIndex(_frameTime));
    }

    public void render (SpriteBatch batch) {
        batch.draw(_currentFrame, 100, 50, 64, 64);

    }
}

