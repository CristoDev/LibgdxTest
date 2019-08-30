package com.mygdx.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.ScreenManager;

public class MainScreen implements Screen {
    private Stage _stage;
    private ScreenManager _manager;


    public MainScreen(ScreenManager manager){
        _manager = manager;
        _stage = new Stage();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
