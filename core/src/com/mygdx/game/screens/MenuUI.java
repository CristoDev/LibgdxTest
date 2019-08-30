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

public class MenuUI  {
    private Stage _stage;
    ScreenManager _manager;

    public MenuUI(ScreenManager manager) {
        _stage=new Stage();
        addMenuButton();
    }

    private void addMenuButton() {
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("my_window.9.png")), 16, 16, 16, 16);
        NinePatchDrawable patchDrawable=new NinePatchDrawable(patch);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(patchDrawable, patchDrawable, patchDrawable, new BitmapFont());
        TextButton button = new TextButton("Menu", style);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Tools.debug("000", "clic retour");
                _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.MenuScreen));

            }
        });
        _stage.addActor(button);
    }

    public void update(float delta){
    }

    public void render(float delta) {
        _stage.act(delta);
        _stage.draw();
    }

    public void show() {
        Gdx.input.setInputProcessor(_stage);
    }

    public void resize(int width, int height) {
        _stage.getViewport().setScreenSize(width, height);
    }

    public void dispose() {
        _stage.clear();
        _stage.dispose();
    }
}
