package com.mygdx.game.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.Tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HighScores {
    private static final String HIGHSCORES_FILE = "highscores.txt";
    private static final String HIGHSCORES_DIRECTORY="tetris/";
    private static final String TOKEN="###";

    public HighScores(String scoring) {
        addScore(scoring);
        loadScores();
    }

    private void addScore(String text) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        writeFile(text + date.format(new Date())+TOKEN);
    }

    private void writeFile(String fileData){
        String fullFilename = HIGHSCORES_DIRECTORY+HIGHSCORES_FILE;

        if( Gdx.files.isLocalStorageAvailable() ) {
            FileHandle file =  Gdx.files.local(fullFilename);
            writeData(fileData, file);
        }
    }

    private void writeData(String fileData, FileHandle file) {
        file.writeString(fileData, true);
    }

    public void loadScores() {
        String fullFilename = HIGHSCORES_DIRECTORY+HIGHSCORES_FILE;

        if( Gdx.files.isLocalStorageAvailable() ) {
            FileHandle file = Gdx.files.local(fullFilename);
            String[] scores=file.readString().split(TOKEN);

            for (int i=0; i<scores.length; i++) {
                Tools.debug("SCORES", scores[i]);
            }
        }
    }
}
