package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.Tools;

public class MenuScreen implements Screen {
    private Stage _stage;
    private ScreenManager _manager;


    public MenuScreen(ScreenManager manager){
        _manager=manager;
        _stage = new Stage();
        //Table table = new Table();
        //table.setFillParent(true);

        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("my_window.9.png")), 16, 16, 16, 16);
        NinePatchDrawable patchDrawable=new NinePatchDrawable(patch);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(patchDrawable, patchDrawable, patchDrawable, new BitmapFont());

        TextButton windowScreen = new TextButton("Window screen", style);
        _stage.addActor(windowScreen);
        //table.add(windowScreen).spaceBottom(10).row();
        //_stage.addActor(table);

        //Listeners
        windowScreen.addListener(new ClickListener() {
                                     @Override
                                     public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                         return true;
                                     }

                                     @Override
                                     public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                         //_manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.MainGame));
                                         Tools.debug("TAG", "clic sur le bouton lancement de window screen");
                                         _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.WindowScreen));
                                     }
                                 }
        );


        TextButton imageScreen = new TextButton("Image application", style);
        imageScreen.setPosition(200, 0);
        _stage.addActor(imageScreen);

        imageScreen.addListener(new ClickListener() {
                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        return true;
                                    }

                                    @Override
                                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                        //_manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.MainGame));
                                        Tools.debug("TAG", "clic sur le bouton lancement de image screen");
                                        _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.ImageScreen));
                                    }
                                }
        );
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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        _stage.act();
        _stage.draw();
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
