package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Tools {

    public Tools() {

    }

    public static void debug(String TAG, String message) {
        Gdx.app.debug(TAG, message);
    }
}
