package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.mygdx.game.ScreenManager;

public class ScrollingScreen extends GlobalScreen {
    private Table container;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "gui/my_window.atlas";
    private final static String STATUSUI_SKIN_PATH = "gui/my_window.json";
    private static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    private static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);

    public ScrollingScreen(ScreenManager manager) {
        _manager=manager;
        _stage=new Stage();

        addScrollPane();
        addMenuButton();
    }

    private void addMenuButton() {
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

    private void addScrollPane() {
        container=new Table();
        _stage.addActor(container);
        container.setFillParent(true);

        Table table=new Table();
        ScrollPane scrollPane = new ScrollPane(table, STATUSUI_SKIN);
        Texture back=new Texture("gui/paper_background_alt.png");
        TextureRegion region=new TextureRegion(back);
        table.setBackground(new TiledDrawable(region));
        Texture texture = new Texture("images/badlogic.png");

        for (int y=0; y<5; y++) {
            table.row();
            for (int i = 0; i < 10; i++) {
                Image img = new Image(texture);
                table.add(img);
            }
        }

        container.add(scrollPane);
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
