package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

import java.util.ArrayList;
import java.util.Iterator;

public class BreakOut {
    private static final String ATLAS="breakout/breakout.pack";

    private Sprite paddle;
    private Vector2 paddlePosition=new Vector2(100, 50);
    private Ball _ball;

    private float paddleWidth, paddleHeight, windowWidth, windowHeight, ballWidth, ballHeight, ballRadius;
    private Rectangle paddleBoundingBox=new Rectangle();
    private TextureAtlas atlas;
    private ArrayList<Brick> bricks=new ArrayList<Brick>();





    /*
    @TODO
    - classe Paddle
    - ajouter une zone pour le jeu a l'crean afin d'avoir le score qq part
    - ajouter les points de vie aux briques (verifier itr.remove)
    - mettredes briques en fonction de la largeur
    - utiliser un tableau pour les briques au lieu des coordonnees
    - ajouter une vitesse pour la balle et le paddle (en fonction de la souris)
    - gestion des bonus, tirs, balles multiples
    - modifier trajectoire en fonction du rebond sur le paddle (limite gauche et droite)
     */

    public BreakOut() {
        windowWidth=Gdx.graphics.getWidth();
        windowHeight=Gdx.graphics.getHeight();
    }

    public void init() {
        atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        createPaddle();
        createBall();
        createBrick();
    }

    private void createPaddle() {
        TextureRegion texture=atlas.findRegion("paddleBlu");
        paddle=new Sprite(texture);
        paddleWidth=texture.getRegionWidth();
        paddleHeight=texture.getRegionHeight();

        paddle.setPosition(paddlePosition.x, paddlePosition.y);
    }

    private void createBall() {
        _ball=new Ball(new Vector2(400, 300), new Vector2(-2f, -2f));
    }

    private void createBrick() {
        for (int i=0; i<10; i++) {
            // @todo modifier la position pour prendre un tableau (le reste doit etre fait dans la classe brick)
            Brick brick=new Brick("green", new Vector2(64*(i+1), 500));
            bricks.add(brick);
            brick=new Brick("purple", new Vector2(64*(i+1), 532));
            bricks.add(brick);
        }
    }




    private void ballCollision() {
        if (_ball.wallCollision(windowWidth, windowHeight)) {
            return ;
        }

        if (_ball.elementCollision(paddleBoundingBox)) {

            return ;
        }

        Iterator itr = bricks.iterator();
        while (itr.hasNext()) {
            Brick brick = (Brick)itr.next();

            if (_ball.elementCollision(brick.getBoundingBox())) {
                Tools.debug("collision avec une brique --- changement de direction à faire");
                itr.remove();
                break;
            }
        }

    }

    public void updateBoundingBox() {
        paddleBoundingBox.set(paddle.getX(), paddle.getY(), paddleWidth, paddleHeight);
        _ball.updateBoundingBox();
    }

    public void update(float delta) {
        updateBoundingBox();
        ballCollision();
        _ball.setPositionBySpeed();

    }

    public void render(SpriteBatch batch) {
        paddle.draw(batch);
        _ball.render(batch);

        for (int i=0; i<bricks.size(); i++) {
            bricks.get(i).render(batch);
        }
    }

    public void setPositionX(int x) {
        if ((x-paddleWidth/2 > -5) && (x+paddleWidth/2 < windowWidth+5)) {
            paddle.setPosition(x - paddleWidth / 2, paddle.getY());
        }
    }
}
