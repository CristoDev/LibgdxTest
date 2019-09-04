package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.Tools;
import com.mygdx.game.window.MyWindow;


public class WindowScreen implements Screen {
    private Stage _stage;
    private ScreenManager _manager;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "gui/my_window.atlas";
    private final static String STATUSUI_SKIN_PATH = "gui/my_window.json";

    private static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    private static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);

    private Image image1;
    private MyWindow winBook, winCode;
    private TextButton button;
    private TextButton.TextButtonStyle  textButtonStyle;
    private int _hpVal = 50;

    private int moveX=5, moveY=2, posX=50, posY=80;
    private double red=0.5, green=0.33, blue=0.84, deltaRed=0.017, deltaGreen=0.021, deltaBlue=0.013;


    public WindowScreen(ScreenManager manager) {
        _manager=manager;
        _stage = new Stage(new ScreenViewport());
        createWindowFromBook();
        createButton();
        createImage();
    }

    private void createWindowFromBook() {

        winBook = new MyWindow("Fenetre BOOK\nblabla titre suite?");
        winBook.createWindowFromBook();
        winBook.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        _stage.addActor(winBook);

        //labels
        Window w=new Window("Titre de fenetre", STATUSUI_SKIN);
        Label hpLabel = new Label("Petite fenetre de test", STATUSUI_SKIN);
        Label hp = new Label(String.valueOf(_hpVal), STATUSUI_SKIN);

        //Add to layout
        w.defaults().expand().fill();

        //account for the title padding
        w.pad(w.getPadTop() + 10, 10, 10, 10);

        w.add(hpLabel);
        w.add(hp).align(Align.left);
        w.row();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.6f, 0.3f, 0.2f, 0.4f);
        pixmap.fill();
        //w.setBackground(new TextureRegionDrawable(new TextureRegion(new  Texture(pixmap))));
        w.setBackground(new TextureRegionDrawable(new  Texture(pixmap)));

        w.pack();

        w.setVisible(true);
        w.setMovable(true);
        w.setPosition(200, 300);
        _stage.addActor(w);
    }


    private void createButton() {
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("gui/my_window_alt.9.png")), 14, 14, 14, 14);
        NinePatchDrawable patchDrawable=new NinePatchDrawable(patch);
        // Create a new TextButtonStyle
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(patchDrawable, patchDrawable, patchDrawable, new BitmapFont());
        // Instantiate the Button itself.
        TextButton button = new TextButton("hello world from blabablabalb and ablblbalbabl\nnew line\n\n\n\n\n\n\n\net 8 de plus", style);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Tools.debug("TAG", "clic sur le bouton, retour au menu");
                _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.MenuScreen));

            }
        });
        // add button to stage

        _stage.addActor(button);
    }

    private void createImage() {
        Texture img = new Texture("images/badlogic.jpg");
        image1 = new Image(img);
        posX=(int)(Gdx.graphics.getWidth()/3-image1.getWidth()/2);
        posY=(int)(Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
        image1.setPosition((int)(Gdx.graphics.getWidth()/3-image1.getWidth()/2),(int)(Gdx.graphics.getHeight()*2/3-image1.getHeight()/2));
        _stage.addActor(image1);

    }


    private void changeColors()
    {
        this.deltaRed=this.changeDelta(this.red, this.deltaRed);
        this.red+=this.deltaRed;

        this.deltaGreen=this.changeDelta(this.green, this.deltaGreen);
        this.green+=this.deltaGreen;

        this.deltaBlue=this.changeDelta(this.blue, this.deltaBlue);
        this.blue+=this.deltaBlue;
    }

    private double changeDelta(double color, double delta)
    {
        if (color < 0 || color > 1)
        {
            delta*=-1;
        }

        return delta;
    }

    private void renderColor() {
        if (posX < 0) {
            moveX=-moveX;
        }

        if (posY < 0) {
            moveY=-moveY;
        }

        if (posX > Gdx.graphics.getWidth()) {
            moveX=-moveX;
        }

        if (posY > Gdx.graphics.getHeight()) {
            moveY=-moveY;
        }

        posX+=moveX;
        posY+=moveY;

        this.changeColors();

        image1.setPosition(posX, posY);
        image1.rotateBy(1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor((float)this.red, (float)this.green, (float)this.blue, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _stage.act();
        _stage.draw();
    }

    @Override
    public void show() {
        if (_stage == null) {
            _stage = new Stage(new ScreenViewport());
            Tools.debug("ProjectScreen", "WARNING: _stage is null");
        }

        Gdx.input.setInputProcessor(_stage);
    }

    @Override
    public void resize(int width, int height) {
        _stage.getViewport().setScreenSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        _stage.clear();
        _stage.dispose();
    }
}
