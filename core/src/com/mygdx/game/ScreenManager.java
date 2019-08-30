package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.MainScreen;
import com.mygdx.game.screens.MenuScreen;

public class ScreenManager extends Game {
    private static MainScreen _mainScreen;
    private static MenuScreen _menuScreen;

    public static enum ScreenType{
        MainMenu,
        MainGame,
    }

    public Screen getScreenType(ScreenType screenType){
        switch(screenType){
            case MainMenu:
                return _menuScreen;
            case MainGame:
                return _mainScreen;
            default:
                return _menuScreen;
        }

    }

    @Override
    public void create(){
        _menuScreen = new MenuScreen(this);
        _mainScreen=new MainScreen(this);
        setScreen(_menuScreen);
    }

    @Override
    public void dispose(){
        _mainScreen.dispose();
        _menuScreen.dispose();
    }

}
