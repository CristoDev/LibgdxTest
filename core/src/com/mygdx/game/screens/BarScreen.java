package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.images.ImageBarBuilder;

public class BarScreen implements Screen {
    private ScreenManager _manager;
    private InputMultiplexer _multiplexer;
    private SpriteBatch batch;
    private MenuUI _menuUI;
    private OrthographicCamera _camera;
    private ImageBarBuilder _barBuilder;

    private static final String TAG = BarScreen.class.getSimpleName();

    public BarScreen(ScreenManager manager) {
        _manager=manager;
        _multiplexer=new InputMultiplexer();
        _barBuilder=new ImageBarBuilder();
        _menuUI=new MenuUI(_manager);

        setupViewport(800, 600);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, ImageMenuScreen.VIEWPORT.viewportWidth, ImageMenuScreen.VIEWPORT.viewportHeight);

        _multiplexer = new InputMultiplexer();
        batch=(SpriteBatch)_barBuilder.getStage().getBatch();
        _multiplexer.addProcessor(_barBuilder.getStage());
        _multiplexer.addProcessor(_menuUI.getStage());
        Gdx.input.setInputProcessor(_multiplexer);
    }

    private void setupViewport(int width, int height){
        ImageMenuScreen.VIEWPORT.virtualWidth = width;
        ImageMenuScreen.VIEWPORT.virtualHeight = height;

        ImageMenuScreen.VIEWPORT.viewportWidth = ImageMenuScreen.VIEWPORT.virtualWidth;
        ImageMenuScreen.VIEWPORT.viewportHeight = ImageMenuScreen.VIEWPORT.virtualHeight;

        ImageMenuScreen.VIEWPORT.physicalWidth = Gdx.graphics.getWidth();
        ImageMenuScreen.VIEWPORT.physicalHeight = Gdx.graphics.getHeight();

        ImageMenuScreen.VIEWPORT.aspectRatio = (ImageMenuScreen.VIEWPORT.virtualWidth / ImageMenuScreen.VIEWPORT.virtualHeight);

        if( ImageMenuScreen.VIEWPORT.physicalWidth / ImageMenuScreen.VIEWPORT.physicalHeight >= ImageMenuScreen.VIEWPORT.aspectRatio){
            ImageMenuScreen.VIEWPORT.viewportWidth = ImageMenuScreen.VIEWPORT.viewportHeight * (ImageMenuScreen.VIEWPORT.physicalWidth/ ImageMenuScreen.VIEWPORT.physicalHeight);
            ImageMenuScreen.VIEWPORT.viewportHeight = ImageMenuScreen.VIEWPORT.virtualHeight;
        }else{
            ImageMenuScreen.VIEWPORT.viewportWidth = ImageMenuScreen.VIEWPORT.virtualWidth;
            ImageMenuScreen.VIEWPORT.viewportHeight = ImageMenuScreen.VIEWPORT.viewportWidth * (ImageMenuScreen.VIEWPORT.physicalHeight/ ImageMenuScreen.VIEWPORT.physicalWidth);
        }

        Gdx.app.debug(TAG, "WorldRenderer: virtual: (" + ImageMenuScreen.VIEWPORT.virtualWidth + "," + ImageMenuScreen.VIEWPORT.virtualHeight + ")" );
        Gdx.app.debug(TAG, "WorldRenderer: viewport: (" + ImageMenuScreen.VIEWPORT.viewportWidth + "," + ImageMenuScreen.VIEWPORT.viewportHeight + ")" );
        Gdx.app.debug(TAG, "WorldRenderer: physical: (" + ImageMenuScreen.VIEWPORT.physicalWidth + "," + ImageMenuScreen.VIEWPORT.physicalHeight + ")" );
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(_multiplexer);
    }

    @Override
    public void render(float delta) {
        _barBuilder.update(delta);
        batch.setProjectionMatrix(_camera.combined);
        batch.begin();

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.end();

        _barBuilder.render(delta);
        _menuUI.render(delta);
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        //_imageBuilder.dispose();
        _menuUI.dispose();
    }
}
