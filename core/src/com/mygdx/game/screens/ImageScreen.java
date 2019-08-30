package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.Tools;
import com.mygdx.game.images.ImageBuilder;

public class ImageScreen extends ProjectScreen {
    SpriteBatch batch;

    private ImageBuilder imageBuilder;
    private MenuUI menuUI;

    public ImageScreen(ScreenManager manager){
        super(manager);

        batch = new SpriteBatch();
        imageBuilder=new ImageBuilder();
        menuUI=new MenuUI(manager);
    }


    @Override
    public void render(float delta) {
        imageBuilder.update(delta);
        menuUI.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        imageBuilder.render(batch);
        //menuUI.render(delta);
        batch.end();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

}
