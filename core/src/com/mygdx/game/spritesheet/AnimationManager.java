package com.mygdx.game.spritesheet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.*;

public class AnimationManager {
    protected static final String TAG=AnimationManager.class.getSimpleName();
    protected static final AssetManager _assetManager = new AssetManager();
    protected static final String ROOTPATH="Universal-LPC-spritesheet/";
    protected String _defaultSpritePath = "body/male/red_orc.png";
    protected static InternalFileHandleResolver _filePathResolver =  new InternalFileHandleResolver();

    protected Pixmap character=null;
    protected List<String> elements=new ArrayList<String>();
    protected float frameDuration=0.1f;
    protected TextureRegion _currentFrame = null;
    protected int FRAME_WIDTH = 64;
    protected int FRAME_HEIGHT = 64;
    protected int FRAME_WIDTH_SCALE = 2;
    protected int FRAME_HEIGHT_SCALE = 2;

    protected Hashtable<AnimationState, Animation<TextureRegion>> _animations;
    protected HashMap<AnimationState, HashMap<String, Animation<TextureRegion>>> _animationsFull;

    protected Animation _currentAnimation;
    protected AnimationState _currentAnimationState;
    protected AnimationDirection _currentAnimationDirection;
    protected int left=9, up=8, down=10, right=11;
    protected float _frameTime = 0f;

    // petu etre utiliser une classe avec une variable reprenant l'enum?
    public static enum AnimationState {
        SPELLCAST(0, 3, 7),
        THRUST(4, 7, 8),
        WALK(8, 11, 9),
        SLASH(12, 15, 6),
        SHOOT(16,19,13),
        HURT(20,20,6);

        private int rowStart, rowEnd, framesCount;

        AnimationState(int start, int end, int count) {
            rowStart=start;
            rowEnd=end;
            framesCount=count;
        }

        public int getRowStart() {
            return rowStart;
        }

        public int getRowEnd() {
            return rowEnd;
        }

        public int getFramesCount() {
            return framesCount;
        }
    }

    public static enum AnimationDirection {
        UP(0),
        LEFT(1),
        DOWN(2),
        RIGHT(3);

        private int index;

        AnimationDirection(int i) {
            index=i;
        }

        public int getIndex() {
            return index;
        }
    }

    public AnimationManager() {
        _animations= new Hashtable<>();
        _animationsFull=new HashMap<>();
        _currentAnimationState=AnimationState.WALK;
        _currentAnimationDirection=AnimationDirection.LEFT;
    }

    private Texture buildTexture() {
        if (elements.size() > 0) {
            character = new Pixmap(Gdx.files.internal(ROOTPATH + _defaultSpritePath));

            for (String element : elements) {
                character.drawPixmap(new Pixmap(Gdx.files.internal(element)), 0, 0);
            }

            return new Texture(character);
        }
        else {
            return getTextureAsset(_defaultSpritePath);
        }
    }

    public void addElement(String filename) {
        elements.add(ROOTPATH+filename);
    }

    protected void loadAnimation(Texture texture, AnimationState animationState) {
        TextureRegion[][] textureFrames = TextureRegion.split(texture, FRAME_WIDTH, FRAME_HEIGHT);
        HashMap<String, Animation<TextureRegion>> animation=new HashMap<>();

        for (AnimationDirection direction : AnimationDirection.values()) {
            Array<TextureRegion> frames = new Array<>(animationState.getFramesCount());

            for (int i = 0; i < animationState.getFramesCount(); i++) {
                TextureRegion region = textureFrames[animationState.getRowStart()+direction.getIndex()][i];
                if (region == null) {
                    Gdx.app.debug(TAG,"loadAnimation::Got null animation frame " + i);
                }
                frames.insert(i, region);
            }

            animation.put(direction.name(), new Animation(frameDuration, frames, Animation.PlayMode.LOOP));
            _animationsFull.put(animationState, animation);

            if (animationState.getRowStart() == animationState.getRowEnd()) {
                break ;
            }
        }
    }

    protected void loadAllAnimations(){
        for (AnimationState state : AnimationState.values()) {
            Texture texture = buildTexture();
            loadAnimation(texture, state);
        }

        HashMap<String, Animation<TextureRegion>> animation=_animationsFull.get(_currentAnimationState);
        _currentAnimation=animation.get(_currentAnimationDirection.name());
        _currentFrame = (TextureRegion)_currentAnimation.getKeyFrame(_frameTime);
    }


    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    public void loadTextureAsset(String textureFilenamePath){
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

    private Texture getTextureAsset(String textureFilenamePath) {
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

    public void update(float delta) {
        _frameTime = (_frameTime + delta)%5; //Want to avoid overflow
        _currentFrame=getCurrentFrame(_frameTime);
    }

    public TextureRegion getCurrentFrame(float _frameTime) {
        return (TextureRegion)_currentAnimation.getKeyFrame(_frameTime);
    }

    public void set_defaultSpritePath(String _defaultSpritePath) {
        this._defaultSpritePath = _defaultSpritePath;
    }

    public void render (SpriteBatch batch, Vector2 position) {
        batch.draw(_currentFrame, position.x, position.y, FRAME_WIDTH*FRAME_WIDTH_SCALE, FRAME_HEIGHT*FRAME_HEIGHT_SCALE);
    }

}
