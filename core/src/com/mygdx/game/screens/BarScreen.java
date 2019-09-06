package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.images.ImageBarBuilder;

public class BarScreen extends GlobalScreen {
    private MenuUI _menuUI;
    private ImageBarBuilder _barBuilder;
    private static final String TAG = BarScreen.class.getSimpleName();

    public BarScreen(ScreenManager manager) {
        _manager=manager;
        _multiplexer=new InputMultiplexer();
        _barBuilder=new ImageBarBuilder();
        _menuUI=new MenuUI(_manager);

        setupViewport(800, 600);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

        _multiplexer = new InputMultiplexer();
        batch=(SpriteBatch)_barBuilder.getStage().getBatch();
        _multiplexer.addProcessor(_barBuilder.getStage());
        _multiplexer.addProcessor(_menuUI.getStage());
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
    public void dispose() {
        _menuUI.dispose();
    }
}
