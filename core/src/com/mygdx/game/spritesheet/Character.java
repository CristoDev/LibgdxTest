package com.mygdx.game.spritesheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class Character {
    protected Animation walk;
    protected static final AssetManager _assetManager = new AssetManager();
    protected static InternalFileHandleResolver _filePathResolver =  new InternalFileHandleResolver();
    protected String _defaultSpritePath = "body/male/red_orc.png";
    protected static final String ROOTPATH="Universal-LPC-spritesheet/";
    protected int left=9, up=8, down=10, right=11;
    protected static String TAG="Character";

    protected Animation _walkLeftAnimation;
    protected Array<TextureRegion> _walkLeftFrames;

    protected float _frameTime = 0f;
    protected float frameDuration=0.07f;
    protected Sprite _frameSprite = null;
    protected TextureRegion _currentFrame = null;

    protected int FRAME_WIDTH = 64;
    protected int FRAME_HEIGHT = 64;

    //abstract protected void loadDefaultSprite();
    //abstract protected void loadAllAnimations();

    public void init() {
        loadTextureAsset(_defaultSpritePath);
        loadDefaultSprite();
        loadAllAnimations();
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

    public static void loadTextureAsset(String textureFilenamePath){

        if( textureFilenamePath == null || textureFilenamePath.isEmpty() ){
            return;
        }

        String textureFilenameRootPath=ROOTPATH+textureFilenamePath;

        if( _assetManager.isLoaded(textureFilenameRootPath) ){
            return;
        }




        if( _filePathResolver.resolve(textureFilenameRootPath).exists() ){
            _assetManager.setLoader(Texture.class, new TextureLoader(_filePathResolver));
            _assetManager.load(textureFilenameRootPath, Texture.class);
            _assetManager.finishLoadingAsset(textureFilenameRootPath);
        }
        else{
            Gdx.app.debug(TAG, "loadTextureAsset::Texture doesn't exist!: " + textureFilenameRootPath );
        }
    }

    public static Texture getTextureAsset(String textureFilenamePath) {
        Texture texture=null;
        String textureFilenameRootPath=ROOTPATH+textureFilenamePath;

        if (_assetManager.isLoaded(textureFilenameRootPath)) {
            texture= _assetManager.get(textureFilenameRootPath, Texture.class);
        }
        else {
            Gdx.app.debug(TAG, "getTextureAsset::Texture is not loaded "+ textureFilenameRootPath);
        }

        return texture;
    }

    public void update(float delta){
        _frameTime = (_frameTime + delta)%5; //Want to avoid overflow
        _currentFrame=(TextureRegion)_walkLeftAnimation.getKeyFrame(_frameTime);
    }


    public Animation getWalk() {
        return walk;
    }

    public void setWalk(Animation walk) {
        this.walk = walk;
    }

    public static AssetManager get_assetManager() {
        return _assetManager;
    }

    public static InternalFileHandleResolver get_filePathResolver() {
        return _filePathResolver;
    }

    public static void set_filePathResolver(InternalFileHandleResolver _filePathResolver) {
        Character._filePathResolver = _filePathResolver;
    }

    public String get_defaultSpritePath() {
        return _defaultSpritePath;
    }

    public void set_defaultSpritePath(String _defaultSpritePath) {
        this._defaultSpritePath = _defaultSpritePath;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public Animation get_walkLeftAnimation() {
        return _walkLeftAnimation;
    }

    public void set_walkLeftAnimation(Animation _walkLeftAnimation) {
        this._walkLeftAnimation = _walkLeftAnimation;
    }

    public Array<TextureRegion> get_walkLeftFrames() {
        return _walkLeftFrames;
    }

    public void set_walkLeftFrames(Array<TextureRegion> _walkLeftFrames) {
        this._walkLeftFrames = _walkLeftFrames;
    }

    public float get_frameTime() {
        return _frameTime;
    }

    public void set_frameTime(float _frameTime) {
        this._frameTime = _frameTime;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(float frameDuration) {
        this.frameDuration = frameDuration;
    }

    public Sprite get_frameSprite() {
        return _frameSprite;
    }

    public void set_frameSprite(Sprite _frameSprite) {
        this._frameSprite = _frameSprite;
    }

    public TextureRegion get_currentFrame() {
        return _currentFrame;
    }

    public void set_currentFrame(TextureRegion _currentFrame) {
        this._currentFrame = _currentFrame;
    }

    public int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public void setFRAME_WIDTH(int FRAME_WIDTH) {
        this.FRAME_WIDTH = FRAME_WIDTH;
    }

    public int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public void setFRAME_HEIGHT(int FRAME_HEIGHT) {
        this.FRAME_HEIGHT = FRAME_HEIGHT;
    }
}
