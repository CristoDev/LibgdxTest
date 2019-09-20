package com.mygdx.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.screens.BreakOutScreen;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title="images";
		config.useGL30=false;
		config.width=800;
		config.height=600;

		Application app=new LwjglApplication(new ScreenManager(), config);

		Gdx.app=app;
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
	}
}
