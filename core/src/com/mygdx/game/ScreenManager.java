package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.*;
import com.mygdx.game.tetris.Grid;
import com.mygdx.game.tetris.GridScreen;

public class ScreenManager extends Game {
    private static MenuScreen _menuScreen;
    private static WindowScreen _windowScreen;
    private static ImageScreen _imageScreen;
    private static ImageMenuScreen _imageMenuScreen;
    private static ScrollingScreen _scrollingScreen;
    private static BarScreen _barScreen;
    private static GridScreen _gridScreen;

    public static enum ScreenType{
        MenuScreen,
        ImageScreen,
        ImageMenuScreen,
        ScrollingScreen,
        BarScreen,
        GridScreen,
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
            case BarScreen:
                return _barScreen;
            case GridScreen:
                return _gridScreen;
            default:
                return _menuScreen;
        }

    }

    @Override
    public void create(){
        _menuScreen = new MenuScreen(this);
        //_windowScreen=new WindowScreen(this);
        //_imageScreen=new ImageScreen(this);
        //_imageMenuScreen=new ImageMenuScreen(this);
        //_scrollingScreen=new ScrollingScreen(this);
        //_barScreen=new BarScreen(this);
        _gridScreen=new GridScreen(this);
        //setScreen(_menuScreen);
        setScreen(_gridScreen);
        debug();
    }

    @Override
    public void dispose(){
        _menuScreen.dispose();
        _windowScreen.dispose();
        _imageScreen.dispose();
        _imageMenuScreen.dispose();
        _scrollingScreen.dispose();
        _barScreen.dispose();
        _gridScreen.dispose();
    }

    private void debug() {
        Grid g=new Grid();
        g.todo();
    }
}