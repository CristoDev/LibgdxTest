package com.mygdx.game.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.screens.MenuUI;

public class TetrisUI extends MenuUI {
    private int _score=0, _lines=0, _level=0;
    private int[] _scoring={40, 100, 300, 1200};
    private Label _scoreLabel, _linesLabel, _levelLabel;
    private Table _infos;

    private final static String STATUSUI_TEXTURE_ATLAS_PATH = "gui/ui_rpg.pack";
    private final static String STATUSUI_SKIN_PATH = "gui/ui_rpg.json";
    private static TextureAtlas STATUSUI_TEXTUREATLAS = new TextureAtlas(STATUSUI_TEXTURE_ATLAS_PATH);
    private static Skin STATUSUI_SKIN = new Skin(Gdx.files.internal(STATUSUI_SKIN_PATH), STATUSUI_TEXTUREATLAS);

    public TetrisUI(ScreenManager manager) {
        super(manager);
        addMenuButton();
        addUI();
    }

    public void addUI() {
        _scoreLabel = new Label("Score: "+_score, STATUSUI_SKIN);
        _linesLabel= new Label("Lignes: "+_lines, STATUSUI_SKIN);
        _levelLabel= new Label("Level: "+_level, STATUSUI_SKIN);

        _infos=new Table();
        _infos.add(_linesLabel);
        _infos.row();
        _infos.add(_levelLabel);
        _infos.row();
        _infos.add(_scoreLabel);
        _infos.row();

        _stage.addActor(_infos);

        _infos.setPosition(50, 500);
    }

    @Override
    public void render(float delta) {
        _scoreLabel.setText("Score: "+_score);
        _linesLabel.setText("Lignes: "+_lines);
        _levelLabel.setText("Level: "+_level);

        _stage.act(delta);
        _stage.draw();

    }


    public void setElements(int nbLines) {
        setScore(nbLines);
        setLines(nbLines);
        setLevel();
    }

    protected void setScore(int nbLines) {
        _score+=_scoring[nbLines]*(_level+1);
    }

    protected void setLines(int nbLines) {
        _lines+=nbLines;
    }

    protected void setLevel() {
        _level=MathUtils.floor(_lines/10);
    }

    public int getLevel() {
        return _level;
    }

}
