package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.game.ScreenManager;

public class MenuUI extends GlobalScreen {
    public MenuUI(ScreenManager manager) {
        _manager=manager;
        _stage=new Stage();
        addMenuButton();
    }

    protected void addMenuButton() {
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("gui/my_window_alt.9.png")), 14, 14, 14, 14);
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
                _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.MenuScreen));

            }
        });

        _stage.addActor(button);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(_stage);
    }

    @Override
    public void render(float delta) {
        _stage.act(delta);
        _stage.draw();
    }

    @Override
    public void dispose() {
        _stage.clear();
        _stage.dispose();
    }
}
