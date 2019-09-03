package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.game.ScreenManager;

public class ScrollingScreen implements Screen {
    private ScreenManager _manager;
    private Stage _stage;
    private Table container;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "my_window.atlas";
    private final static String STATUSUI_SKIN_PATH = "my_window.json";

    private static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    private static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);



    public ScrollingScreen(ScreenManager manager) {
        _manager=manager;
        _stage=new Stage();
        addMenuButton();
        addScrollPane();
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
                _manager.setScreen(_manager.getScreenType(ScreenManager.ScreenType.MenuScreen));

            }
        });

        _stage.addActor(button);
    }

    private void addScrollPane() {
        container=new Table();
        _stage.addActor(container);
        container.setFillParent(true);

        Table table=new Table();

        ScrollPane scrollPane = new ScrollPane(table, STATUSUI_SKIN);
        Texture texture = new Texture("badlogic.jpg");

        for (int y=0; y<5; y++) {
            table.row();
            for (int i = 0; i < 10; i++) {
                Image img = new Image(texture);
                //img.setRotation((90*i)%360);
                table.add(img);
            }
        }

        container.add(scrollPane).expand().fill().colspan(4);
        scrollPane.setFillParent(true);
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
        _stage.clear();
        _stage.dispose();
    }
}
