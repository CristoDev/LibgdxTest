package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Stage stage;
	private Image image1;

	private int moveX=5, moveY=2, posX=0, posY=0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		stage = new Stage(new ScreenViewport());
		image1 = new Image(img);
		posX=(int)(Gdx.graphics.getWidth()/3-image1.getWidth()/2);
		posY=(int)(Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
		image1.setPosition((int)(Gdx.graphics.getWidth()/3-image1.getWidth()/2),(int)(Gdx.graphics.getHeight()*2/3-image1.getHeight()/2));
		stage.addActor(image1);
	}

	@Override
	public void render () {
		if (posX < 0) {
			moveX=-moveX;
		}

		if (posY < 0) {
			moveY=-moveY;
		}

		if (posX > Gdx.graphics.getWidth()) {
			moveX=-moveX;
		}

		if (posY > Gdx.graphics.getHeight()) {
			moveY=-moveY;
		}

		posX+=moveX;
		posY+=moveY;

		//image1.moveBy(moveX, moveY);
		image1.setPosition(posX, posY);
		image1.rotateBy(1);

		Gdx.gl.glClearColor(1, (float)0.4, (float)0.1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*
		batch.begin();
		batch.draw(img, posX, posY);
		batch.end();

		 */
		stage.act();
		stage.draw();
	}

}
