package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.Tools;

import java.util.ArrayList;

public class Level {
    private int _cols=8, _rows=10;
    private static int _level=1;
    private static final String FILENAME="breakout/levels";
    private static String data=null;
    private ArrayList<String> levelData=new ArrayList<>();

    public Level(int cols, int rows) {
        this(cols, rows, 1);
    }

    public Level(int cols, int rows, int level) {
        _cols=cols;
        _rows=rows;
        _level=level;
        init();
    }

    private void init() {
        boolean doesProfileFileExist = Gdx.files.local(FILENAME).exists();

        if( !doesProfileFileExist ){
            Tools.debug("fichier non trouve");
            return;
        }

        data = new FileHandle(FILENAME).readString();
    }

    public ArrayList<String> getLevelData() {
        String token="#level_"+_level;
        String[] tokens = data.substring(data.indexOf(token)+token.length(), data.indexOf("#"+token)).split("\r\n");

        for (int i=0; i<Math.min(_rows, tokens.length); i++) {
            if (tokens[i].length() == _cols) {
                levelData.add(tokens[i]);
            }
        }

        return levelData;
    }

    public void nextLevel() {
        _level++;
    }

}
