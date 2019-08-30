package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.images.CompositeSprite;
import com.mygdx.game.images.ImageApplication;
import com.mygdx.game.spritesheet.AnimationManager;
import com.mygdx.game.spritesheet.Character;


///////////////// reprend le code de ImageApplication

public class ImageScreen implements Screen {
    private ScreenManager _manager;
    SpriteBatch batch;
    Texture img, img2, img3;
    int x=0, y=0, dx=1, dy=2, nb=30;
    private static final String TAG = ImageApplication.class.getSimpleName();

    Array<Vector2> positions=new Array<Vector2>();
    Array<Vector2> deltas=new Array<Vector2>();

    Character p=null;
    Character o=null;

    CompositeSprite sprite=null;


    public ImageScreen(ScreenManager manager) {
        _manager=manager;
        create();
    }

    @Override
    public void show() {

    }

    private void updatePosition() {
        for (int i=0; i<nb; i++) {
            float dx=deltas.get(i).x, dy=deltas.get(i).y;

            if (positions.get(i).x >=200) {
                dx=-1;
            }

            if (positions.get(i).y >=400) {
                dy=-1;
            }

            if (positions.get(i).x <=0) {
                dx=1;
            }

            if (positions.get(i).y <=0) {
                dy=1;
            }

            positions.set(i, new Vector2(positions.get(i).x+dx, positions.get(i).y+dy));
            deltas.set(i, new Vector2(dx, dy));
        }

    }

    public void update(float delta){
        if (p != null)
            p.update(delta);

        if (o != null)
            o.update(delta);
    }

    @Override
    public void render(float delta) {
        updatePosition();
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(0.6f, 0.6f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(img, x, y);
        batch.draw(img2, x, y);
        batch.draw(img3, y+50, x*2);
        if (p != null)
            p.render(batch);

        if (o != null)
            o.render(batch);

        sprite.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        img2.dispose();
        img3.dispose();
    }



    public void create () {
        batch = new SpriteBatch();
        createTexture();

        p=new Character();
        o=new Character();

        if (p != null) {
            p.createHuman();
            p.init();
            p.setPosition(200, 50);
            p.addEquipment("torso/chain/mail_male.png");
            p.addEquipment("hands/gloves/male/metal_gloves_male.png");
            p.addEquipment("weapons/right hand/male/dagger_male.png");
            p.setAnimationDirection(AnimationManager.AnimationDirection.RIGHT);
            p.loadAllAnimations();
        }

        if (o != null) {
            o.createOrc();
            o.init();
            o.setPosition(50, 50);
            o.addEquipment("weapons/right hand/male/spear_male.png");
            o.setAnimationDirection(AnimationManager.AnimationDirection.UP);
            o.setAnimationState(AnimationManager.AnimationState.HURT);
            o.loadAllAnimations();
        }

        createSprite();
        //SpriteSheet spriteSheet=new SpriteSheet();
    }

    private void createTexture() {
        img = new Texture("images/img_01.png");
        img2=new Texture("images/img_02.png");

        // creation d'une texture a partir de 3 images
        Pixmap pix=new Pixmap(Gdx.files.internal("images/badlogic.jpg"));
        pix.drawPixmap(new Pixmap(Gdx.files.internal("images/img_01.png")), 0, 0);
        pix.drawPixmap(new Pixmap(Gdx.files.internal("images/img_02.png")), 0, 0);
        img3=new Texture(pix);

        for (int i=0; i<nb; i++) {
            positions.add(new Vector2(MathUtils.random(0, 200), MathUtils.random(0, 300)));
            deltas.add(new Vector2(1, 1));
        }


    }

    public void createSprite() {
        sprite=new CompositeSprite();

        Sprite tmp0=new Sprite(img);
        sprite.addComponentSprite(tmp0);

        Sprite tmp1=new Sprite(img2);
        sprite.addComponentSprite(tmp1, 300, 100);
    }




}