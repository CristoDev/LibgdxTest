package com.mygdx.game.desktop;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.images.Image;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title="BludBourne v0.8";
		config.useGL30=false;
		config.width=1027;
		config.height=768;

		Application app=new LwjglApplication(new Image(), config);
		//Application app=new LwjglApplication(new MyGdxGame(), config);

		Gdx.app=app;
		//Gdx.app.setLogLevel(Application.LOG_INFO);
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		//Gdx.app.setLogLevel(Application.LOG_ERROR);
		//Gdx.app.setLogLevel(Application.LOG_NONE);
		//Gdx.app.debug(TAG, "");
	}
}
