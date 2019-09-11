package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.images.ImageBuilder;

public class ImageMenuScreen extends GlobalScreen  {
    private ImageBuilder _imageBuilder;
    private MenuUI _menuUI;

    private static final String TAG = ImageMenuScreen.class.getSimpleName();

    public ImageMenuScreen(ScreenManager manager) {
        _manager=manager;
        batch=new SpriteBatch();
        _imageBuilder=new ImageBuilder();

        setupViewport(800, 600);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

        _menuUI=new MenuUI(_manager);

        _multiplexer = new InputMultiplexer();
        _multiplexer.addProcessor(_menuUI.getStage());
        batch=(SpriteBatch)_menuUI.getStage().getBatch();

        Gdx.input.setInputProcessor(_multiplexer);
    }

    @Override
    public void render(float delta) {
        _imageBuilder.update(delta);
        batch.setProjectionMatrix(_camera.combined);
        batch.begin();

        Gdx.gl.glClearColor(0f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _imageBuilder.render(batch);
        batch.end();

        _menuUI.render(delta);
    }

    @Override
    public void dispose() {
        _imageBuilder.dispose();
        _menuUI.dispose();
    }
}
