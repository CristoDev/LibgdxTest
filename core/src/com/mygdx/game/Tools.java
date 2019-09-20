package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class Tools {
    private I18NBundle i18n;

    public Tools() {

    }

    public I18NBundle getI18n() {
        return i18n;
    }

    public String get(String key) {
        return i18n.get(key);
    }

    public String format (String key, Object... args) {
        return i18n.format(key, args);
    }

    private void initI18n(String lang) {
        FileHandle baseFileHandle = Gdx.files.internal("i18n/default");
        Locale locale = new Locale(lang);
        i18n = I18NBundle.createBundle(baseFileHandle, locale);
    }

    public void initI18nDefault() {
        FileHandle baseFileHandle = Gdx.files.internal("i18n/default");
        i18n = I18NBundle.createBundle(baseFileHandle);
    }

    public void initI18nFR() {
        initI18nDefault();
    }

    public void initI18nEN() {
        initI18n("en");
    }

    public static void debug(String TAG, String message) {
        Gdx.app.debug(TAG, message);
    }

    public static void debug(String message) {
        Gdx.app.debug("DEBUG", message);
    }
}
