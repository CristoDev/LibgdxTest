package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;


public class MyWindow extends Window {
    public final AssetManager manager = new AssetManager();
    private TextureAtlas atlas;

    private int _hpVal = 50;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "statusui.atlas";
    private final static String STATUSUI_SKIN_PATH = "statusui.json";

    public static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    public static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);


    public MyWindow(String title){
        super(title, STATUSUI_SKIN);
    }

    public void createWindowFromBook() {
        //labels
        Label hpLabel = new Label(" hp:", STATUSUI_SKIN);
        Label hp = new Label(String.valueOf(_hpVal), STATUSUI_SKIN);

        //Add to layout
        defaults().expand().fill();

        //account for the title padding
        this.pad(this.getPadTop() + 10, 10, 10, 10);

        this.add(hpLabel);
        this.add(hp).align(Align.left);
        this.row();

        this.pack();
    }

    public void createWindowFromCode() {
        //Add to layout
        defaults().expand().fill();

        //account for the title padding
        this.pad(this.getPadTop() + 10, 10, 10, 10);

        Label hpLabel = new Label(" hp CODE:", STATUSUI_SKIN);
        this.add(hpLabel);

        this.pack();
    }

}
