package com.mygdx.game.images;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.mygdx.game.Tools;

public class ImageBarBuilder implements Screen {
    private int x=200, y=100;
    private static final String TAG = ImageBarBuilder.class.getSimpleName();
    private Image _imageHP, _imageMP;
    private Stage _stage;
    private float _timeHP=0, _timeMP=0, _durationHP=3, _durationMP=1;
    private TextButton _buttonMP, _buttonHP, _buttonSkin;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "gui/statusui.atlas";
    private final static String STATUSUI_SKIN_PATH = "gui/statusui.json";
    public static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    public static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);


    public ImageBarBuilder() {
        _stage=new Stage();
        create();
        addMenuButton();
        addNewButton();
    }


    public void create () {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("gui/statusui.atlas"));
        _imageMP=new Image(atlas.findRegion("MP_Bar"));
        _imageHP=new Image(atlas.findRegion("HP_Bar"));

        _imageMP.setPosition(x, y);
        _imageHP.setPosition(x, y+50);

        _stage.addActor(_imageMP);
        _stage.addActor(_imageHP);
    }

    private void addNewButton() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/impact.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont fooFont = generator.generateFont(parameter);
        generator.dispose();

        Skin menuSkin = new Skin();
        menuSkin.add("myFont", fooFont);
        menuSkin.load(Gdx.files.internal("gui/statusui_new.json"));
        _buttonSkin=new TextButton("Essai BLABLA", menuSkin, "inventory");
        _buttonSkin.setPosition(x, y+200);

        _buttonSkin.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Tools.debug(TAG, "Clic sur le nouveau bouton");
            }
        });
        _stage.addActor(_buttonSkin);

        /*
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("gui/my_window_alt.9.png")), 14, 14, 14, 14);
        NinePatchDrawable patchDrawable=new NinePatchDrawable(patch);
        FreeTypeFontGenerator ftfGen = new FreeTypeFontGenerator(Gdx.files.internal("fonts/impact.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 16;
        BitmapFont font12 = ftfGen.generateFont(params);
        ftfGen.dispose();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(patchDrawable, patchDrawable, patchDrawable, font12);

        _buttonMP= new TextButton("Attaque MP", style);
         */
    }

    private void addMenuButton() {
        NinePatch patch = new NinePatch(new Texture(Gdx.files.internal("gui/my_window_alt.9.png")), 14, 14, 14, 14);
        NinePatchDrawable patchDrawable=new NinePatchDrawable(patch);
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(patchDrawable, patchDrawable, patchDrawable, new BitmapFont());

        // permet de tester le changement de couleur pour le texte du bouton
        _buttonMP=new TextButton("Attaque MP", STATUSUI_SKIN, "inventory");

        /*

*/

        _buttonMP.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Tools.debug(TAG, "Attaque MP activée depuis le bouton -----");
                _timeMP=0;
                _buttonMP.setDisabled(true);
                _buttonMP.setTouchable(Touchable.disabled);
            }
        });

        _buttonHP= new TextButton("Attaque HP", style);
        _buttonHP.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Tools.debug(TAG, "Attaque HP activée depuis le bouton +++++");
                _timeHP=0;
                _buttonHP.setDisabled(true);
                _buttonHP.setTouchable(Touchable.disabled);
            }
        });

        _buttonHP.setDisabled(true);
        _buttonHP.setTouchable(Touchable.disabled);
        _buttonMP.setDisabled(true);
        _buttonMP.setTouchable(Touchable.disabled);

        _buttonMP.setPosition(0, y);
        _buttonHP.setPosition(0, y+50);

        _stage.addActor(_buttonMP);
        _stage.addActor(_buttonHP);
    }

    public void update(float delta) {
        _timeHP+=delta;
        _timeMP+=delta;

        _imageHP.setScale(MathUtils.clamp(_timeHP/_durationHP, 0, 1), 1);
        _imageMP.setScale(MathUtils.clamp(_timeMP/_durationMP, 0, 1), 1);

        if (_timeHP > _durationHP && _buttonHP.isDisabled()) {
            Tools.debug(TAG, "Activation HP +++++");
            _buttonHP.setDisabled(false);
            _buttonHP.setTouchable(Touchable.enabled);
        }

        if (_timeMP > _durationMP && _buttonMP.isDisabled()) {
            Tools.debug(TAG, "Activation MP -----");
            _buttonMP.setDisabled(false);
            _buttonMP.setTouchable(Touchable.enabled);
        }
    }

    public Stage getStage() {
        return _stage;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        _stage.act(delta);
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
    }

    public void dispose () {

    }
}
