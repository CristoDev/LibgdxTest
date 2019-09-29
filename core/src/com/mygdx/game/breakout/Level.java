package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Tools;

import java.util.ArrayList;

public class Level {
    private int _cols=8, _rows=10;
    private static int _level=1;
    private static final String FILENAME="breakout/levels";
    private static String data=null;
    private ArrayList<String> levelData=new ArrayList<>();
    private int _brickWidth, _brickHeight;
    private float _playZoneHeight;
    private ArrayList<Brick> bricks=new ArrayList<Brick>();

    public Level(int cols, int rows, int brickWidth, int brickHeight, float playZoneHeight) {
        this(cols, rows, brickWidth, brickHeight, playZoneHeight, 1);
    }

    public Level(int cols, int rows, int brickWidth, int brickHeight, float playZoneHeight, int level) {
        _cols=cols;
        _rows=rows;
        _level=level;
        _brickHeight=brickHeight;
        _brickWidth=brickWidth;
        _playZoneHeight=playZoneHeight;
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

    public ArrayList<Brick> createLevel() {
        String token="#level_"+_level;
        String[] tokens = data.substring(data.indexOf(token)+token.length(), data.indexOf("#"+token)).split("\n");

        for (int i=0; i<Math.min(_rows, tokens.length); i++) {
            if (tokens[i].length() == _cols) {
                createLine(i, tokens[i]);
            }
        }

        return bricks;
    }

    public void nextLevel() {
        _level++;
    }

    public void createLine(int row, String line) {
        for (int col=0; col<line.length(); col++) {
            if (line.charAt(col) == 'Z') {
                continue ;
            }
            else {
                bricks.add(new Brick(Integer.parseInt(String.valueOf(line.charAt(col))), new Vector2(col*_brickWidth, _playZoneHeight-_brickHeight*row), 1));
            }
        }
    }

}
