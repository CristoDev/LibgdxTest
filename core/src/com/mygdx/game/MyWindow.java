package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


public class MyWindow extends Window {
    public final AssetManager manager = new AssetManager();
    private TextureAtlas atlas;

    private int _hpVal = 50;

    // l'atlas contient deja les donnees 9-pacth! (voir dialogDim et default*)
    //private final static String STATUSUI_TEXTURE_ATLAS_PATH = "statusui.atlas";
    //private final static String STATUSUI_SKIN_PATH = "statusui.json";


    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "my_window.atlas";
    private final static String STATUSUI_SKIN_PATH = "my_window.json";

    private static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    private static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);


    public MyWindow(String title){
        super(title, STATUSUI_SKIN);
    }


    public void createWindowFromBook() {
        //labels
        Label hpLabel = new Label(" hppppppppppppppppppppppppppp:\n\n\npassage de 3 lignes", STATUSUI_SKIN);
        Label hp = new Label(String.valueOf(_hpVal), STATUSUI_SKIN);

        //Add to layout
        defaults().expand().fill();

        //account for the title padding
        pad(this.getPadTop() + 10, 10, 10, 10);

        add(hpLabel);
        add(hp).align(Align.left);
        row();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.1f, 0.2f, 0.5f, 1);
        pixmap.fill();
        //setBackground(new TextureRegionDrawable(new TextureRegion(new  Texture(pixmap))));
        setBackground(new TextureRegionDrawable(new  Texture(pixmap)));

        pack();

        setVisible(true);
        setMovable(true);
    }


}
