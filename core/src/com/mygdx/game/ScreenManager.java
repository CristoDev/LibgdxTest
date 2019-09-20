package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.*;

public class ScreenManager extends Game {
    private static MenuScreen _menuScreen;
    private static BreakOutScreen _breakOutScreen;

    public static enum ScreenType{
        MenuScreen,
        BreakOutScreen;
    }

    public Screen getScreenType(ScreenType screenType){
        switch(screenType){
            case MenuScreen:
                return _menuScreen;
            case BreakOutScreen:
                return _breakOutScreen;
            default:
                return _menuScreen;
        }

    }

    @Override
    public void create(){
        _menuScreen = new MenuScreen(this);
        _breakOutScreen = new BreakOutScreen(this);
        setScreen(_menuScreen);
    }

    @Override
    public void dispose(){
        _menuScreen.dispose();
        _breakOutScreen.dispose();
    }
}