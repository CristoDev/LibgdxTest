package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.*;

public class ScreenManager extends Game {
    private static MenuScreen _menuScreen;
    private static WindowScreen _windowScreen;
    private static ImageScreen _imageScreen;
    private static ImageMenuScreen _imageMenuScreen;
    private static ScrollingScreen _scrollingScreen;

    public static enum ScreenType{
        MenuScreen,
        ImageScreen,
        ImageMenuScreen,
        ScrollingScreen,
        WindowScreen;
    }

    public Screen getScreenType(ScreenType screenType){
        switch(screenType){
            case MenuScreen:
                return _menuScreen;
            case WindowScreen:
                return _windowScreen;
            case ImageScreen:
                return _imageScreen;
            case ImageMenuScreen:
                return _imageMenuScreen;
            case ScrollingScreen:
                return _scrollingScreen;
            default:
                return _menuScreen;
        }

    }

    @Override
    public void create(){
        _menuScreen = new MenuScreen(this);
        _windowScreen=new WindowScreen(this);
        _imageScreen=new ImageScreen(this);
        _imageMenuScreen=new ImageMenuScreen(this);
        _scrollingScreen=new ScrollingScreen(this);
        setScreen(_menuScreen);
    }

    @Override
    public void dispose(){
        _menuScreen.dispose();
        _windowScreen.dispose();
        _imageScreen.dispose();
        _imageMenuScreen.dispose();
        _scrollingScreen.dispose();
    }

}
