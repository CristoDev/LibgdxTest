package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.*;

public class ScreenManager extends Game {
    private static MenuScreen _menuScreen;

    public static enum ScreenType{
        MenuScreen;
    }

    public Screen getScreenType(ScreenType screenType){
        switch(screenType){
            case MenuScreen:
                return _menuScreen;
            default:
                return _menuScreen;
        }

    }

    @Override
    public void create(){
        _menuScreen = new MenuScreen(this);
        setScreen(_menuScreen);
    }

    @Override
    public void dispose(){
        _menuScreen.dispose();
    }
}