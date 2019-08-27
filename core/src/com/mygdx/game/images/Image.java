package com.mygdx.game.images;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Image extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img, img2, img3;
    int x=0, y=0, dx=1, dy=2, nb=30;
    private static final String TAG = Player.class.getSimpleName();

    //Array<Texture> images=new Array<Texture>();
    Array<Vector2> positions=new Array<Vector2>();
    Array<Vector2> deltas=new Array<Vector2>();

    Player p=new Player();
    CompositeSprite sprite=null;
    public final int FRAME_WIDTH = 64;
    public final int FRAME_HEIGHT = 64;

    private Animation _walkLeftAnimation;
    private Array<TextureRegion> _walkLeftFrames;
    protected float _frameTime = 0f;
    protected Sprite _frameSprite = null;
    protected TextureRegion _currentFrame = null;

    int left=9, up=8, down=10, right=11;


    @Override
    public void create () {
        batch = new SpriteBatch();
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

        p.init();
        createSprite();
        createCombinedSprites();

        Gdx.app.debug(TAG, "local path:" +Gdx.files.getLocalStoragePath());
        FileHandle hfile=Gdx.files.internal("./");
        FileHandle[] data=hfile.list();

        for (int i=0; i<data.length; i++) {
            FileHandle tmp=data[i];
            if (tmp.isDirectory()) {
                Gdx.app.debug(TAG, "repertoire "+tmp.name());
                FileHandle xfile=Gdx.files.internal(tmp.path());
                FileHandle[] xdata=xfile.list();
                for (int xi=0; xi<xdata.length; xi++) {
                    FileHandle xtmp = xdata[xi];
                    Gdx.app.debug(TAG, "N2 "+xtmp.name());
                }

            }
            else {
                Gdx.app.debug(TAG, "fichier "+tmp.name());
            }
        }

    }

    public void createSprite() {
        sprite=new CompositeSprite();

        Sprite tmp0=new Sprite(img);
        //tmp0.setPosition(300, 400);

        sprite.addComponentSprite(tmp0);

        Sprite tmp1=new Sprite(img2);
        //tmp1.setPosition(300, 400);
        sprite.addComponentSprite(tmp1, 300, 100);




    }

    public void createCombinedSprites() {
        Pixmap mail=new Pixmap(Gdx.files.internal("images/light.png"));
        // pour l'ordre utiliser linkedHashMap
        mail.drawPixmap(new Pixmap(Gdx.files.internal("images/mail_male.png")), 0, 0);
        mail.drawPixmap(new Pixmap(Gdx.files.internal("images/jacket_male.png")), 0, 0);
        mail.drawPixmap(new Pixmap(Gdx.files.internal("images/light-blonde.png")), 0, 0);

        Texture texture = new Texture(mail);
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
        _frameTime = (_frameTime + delta)%5; //Want to avoid overflow
        _currentFrame=(TextureRegion)_walkLeftAnimation.getKeyFrame(_frameTime);
        p.update(delta);
    }

    @Override
    public void render () {
        updatePosition();
        update(Gdx.graphics.getDeltaTime());
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        //batch.draw(img, x, y);
        //batch.draw(img2, x, y);
        //batch.draw(img3, y+50, x*2);
        p.render(batch);
        sprite.draw(batch);
        batch.draw(_currentFrame, 200, 200, 64, 64);

		/*
		for (int i=0; i<nb; i++) {
			batch.draw(img3, positions.get(i).x, positions.get(i).y);
		}
		 */

        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}