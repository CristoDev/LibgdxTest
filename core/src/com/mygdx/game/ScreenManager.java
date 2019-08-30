package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screens.ImageMenuScreen;
import com.mygdx.game.screens.ImageScreen;
import com.mygdx.game.screens.MenuScreen;
import com.mygdx.game.screens.WindowScreen;

public class ScreenManager extends Game {
    private static MenuScreen _menuScreen;
    private static WindowScreen _windowScreen;
    private static ImageScreen _imageScreen;
    private static ImageMenuScreen _imageMenuScreen;

    public static enum ScreenType{
        MenuScreen,
        ImageScreen,
        ImageMenuScreen,
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
        setScreen(_menuScreen);
    }

    @Override
    public void dispose(){
        _menuScreen.dispose();
        _windowScreen.dispose();
        _imageScreen.dispose();
        _imageMenuScreen.dispose();
    }

}
