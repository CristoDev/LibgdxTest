package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.Tools;
import com.mygdx.game.breakout.BreakOut;

public class BreakOutScreen extends GlobalScreen implements InputProcessor {
    private BreakOut _breakOut=null;
    private Table _table;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "gui/ui_rpg.pack";
    private final static String STATUSUI_SKIN_PATH = "gui/ui_rpg.json";
    private static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    private static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);

    public BreakOutScreen(ScreenManager manager) {
        _manager=manager;
        _stage = new Stage();

        init();
    }

    private void init() {
        batch=new SpriteBatch();

        setupViewport(800, 600);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

        _multiplexer = new InputMultiplexer();
        _multiplexer.addProcessor(this);
        _multiplexer.addProcessor(_stage);
        Gdx.input.setInputProcessor(_multiplexer);

        initInterface();
        buildInterface();
        buildGame();

    }

    private void buildGame() {
        _breakOut=new BreakOut();
        _breakOut.init();

    }

    private void initInterface() {
        _table=new Table();
        //_table.defaults().expand().fill();

        Pixmap bgPixmap = new Pixmap(1,1, Pixmap.Format.RGB565);
        bgPixmap.setColor(Color.BLACK);
        bgPixmap.fill();
        _table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture(bgPixmap))));

        _table.setPosition(640, 0);
        _table.pack();
        _stage.addActor(_table);

        _table.setSize(160, 600);

    }

    private void buildInterface() {
        Label _scoreLabel = new Label("Score, vie\n, niveau ", STATUSUI_SKIN);
        _table.add(_scoreLabel);

    }

    public void update(float delta) {
        _breakOut.update(delta);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        update(delta);

        batch.setProjectionMatrix(_camera.combined);
        batch.begin();

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        _breakOut.render(batch);


        batch.end();

        _stage.act();
        _stage.draw();

    }

    @Override
    public void dispose() {
        _stage.clear();
        _stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Tools.debug("touchdown, position "+screenX+" / "+screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        _breakOut.setPositionX(screenX);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
