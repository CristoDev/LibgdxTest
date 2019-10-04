package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.test.I18nTest;

public class MenuScreen extends GlobalScreen {

    public MenuScreen(ScreenManager manager){
        _manager=manager;
        _stage = new Stage();

        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("gui/my_window_alt.9.png")), 14, 14, 14, 14);
        NinePatchDrawable patchDrawable=new NinePatchDrawable(patch);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(patchDrawable, patchDrawable, patchDrawable, new BitmapFont());

        TextButton windowScreen = new TextButton("Window screen", style);
        _stage.addActor(windowScreen);

        //Listeners
        windowScreen.addListener(new ClickListener() {
                                     @Override
                                     public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                         return true;
                                     }

                                     @Override
                                     public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                         _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.WindowScreen));
                                     }
                                 }
        );

        TextButton imageScreen = new TextButton("Image application", style);
        imageScreen.setPosition(150, 0);
        _stage.addActor(imageScreen);

        imageScreen.addListener(new ClickListener() {
                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        return true;
                                    }

                                    @Override
                                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                        _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.ImageScreen));
                                    }
                                }
        );

        TextButton imageMenuScreen = new TextButton("Image + menu", style);
        imageMenuScreen.setPosition(300, 0);
        _stage.addActor(imageMenuScreen);

        imageMenuScreen.addListener(new ClickListener() {
                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                        return true;
                                    }

                                    @Override
                                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                        _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.ImageMenuScreen));
                                    }
                                }
        );

        TextButton scrollingScreen = new TextButton("Scrolling", style);
        scrollingScreen.setPosition(450, 0);
        _stage.addActor(scrollingScreen);

        scrollingScreen.addListener(new ClickListener() {
                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                            return true;
                                        }

                                        @Override
                                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                            _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.ScrollingScreen));
                                        }
                                    }
        );

        TextButton barScreen = new TextButton("Bars", style);
        barScreen.setPosition(500, 0);
        _stage.addActor(barScreen);

        barScreen.addListener(new ClickListener() {
                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                            return true;
                                        }

                                        @Override
                                        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                            _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.BarScreen));
                                        }
                                    }
        );

        I18nTest test=new I18nTest();
        test.run();

        TextButton gridScreen = new TextButton("Tetris", style);
        gridScreen.setPosition(600, 0);
        _stage.addActor(gridScreen);

        gridScreen.addListener(new ClickListener() {
                                  @Override
                                  public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                                      return true;
                                  }

                                  @Override
                                  public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                                      _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.GridScreen));
                                  }
                              }
        );
    }

    @Override
    public void show() {
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
    public void dispose() {
        _stage.clear();
        _stage.dispose();
    }
}
