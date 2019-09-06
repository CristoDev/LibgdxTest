package com.mygdx.game.spritesheet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

public class Character {
    protected String _defaultSpritePath = "body/male/red_orc.png";
    protected static String TAG="Character";

    protected Vector2 position=new Vector2(0, 0);
    protected AnimationManager animationManager=new AnimationManager();

    public void init() {
        Tools.debug(TAG, "default sprite path "+_defaultSpritePath);
        animationManager.set_defaultSpritePath(_defaultSpritePath);
        animationManager.loadTextureAsset(_defaultSpritePath);
        //animationManager.loadDefaultSprite();
    }

    public void loadAllAnimations() {
        animationManager.loadAllAnimations();
    }

    public void loadAllAnimationsFromDefaultFile() {
        animationManager.loadAllAnimationsFromDefaultFile();
    }

    public void update(float delta){
        animationManager.update(delta);
    }

    public void render (SpriteBatch batch) {
        animationManager.render(batch, position);
    }

    public void createOrc() {
        set_defaultSpritePath("body/male/red_orc.png");
    }

    public void createHuman() {
        set_defaultSpritePath("body/male/dark.png");
    }

    public void set_defaultSpritePath(String _defaultSpritePath) {
        this._defaultSpritePath = _defaultSpritePath;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(int x, int y) {
        setPosition(new Vector2(x, y));
    }

    public void addEquipment(String filename) {
        animationManager.addElement(filename);
    }

    public void setAnimationState(AnimationManager.AnimationState state) {
        animationManager.setAnimationState(state);
    }

    public void setAnimationDirection(AnimationManager.AnimationDirection direction) {
        animationManager.setAnimationDirection(direction);

    }
}
