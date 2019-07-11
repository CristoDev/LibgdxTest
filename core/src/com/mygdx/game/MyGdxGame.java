package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MyGdxGame extends ApplicationAdapter {
	//private SpriteBatch batch;
	private Stage stage;
	private Image image1;

	private int moveX=5, moveY=2, posX=50, posY=80;
	private double red=0.5, green=0.33, blue=0.84, deltaRed=0.017, deltaGreen=0.021, deltaBlue=0.013;
	
	@Override
	public void create () {
		Texture img = new Texture("badlogic.jpg");
		//batch = new SpriteBatch();
		stage = new Stage(new ScreenViewport());
		image1 = new Image(img);
		posX=(int)(Gdx.graphics.getWidth()/3-image1.getWidth()/2);
		posY=(int)(Gdx.graphics.getHeight()*2/3-image1.getHeight()/2);
		image1.setPosition((int)(Gdx.graphics.getWidth()/3-image1.getWidth()/2),(int)(Gdx.graphics.getHeight()*2/3-image1.getHeight()/2));
		stage.addActor(image1);
	}

	private void changeColors()
	{
		this.deltaRed=this.changeDelta(this.red, this.deltaRed);
		this.red+=this.deltaRed;

		this.deltaGreen=this.changeDelta(this.green, this.deltaGreen);
		this.green+=this.deltaGreen;

		this.deltaBlue=this.changeDelta(this.blue, this.deltaBlue);
		this.blue+=this.deltaBlue;
	}

	private double changeDelta(double color, double delta)
	{
		if (color < 0 || color > 1)
		{
			delta*=-1;
		}

		return delta;
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

		this.changeColors();

		//image1.moveBy(moveX, moveY);
		image1.setPosition(posX, posY);
		image1.rotateBy(1);

		Gdx.gl.glClearColor((float)this.red, (float)this.green, (float)this.blue, 1);
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
