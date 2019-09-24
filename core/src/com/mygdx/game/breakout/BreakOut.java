package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

import java.util.ArrayList;
import java.util.Iterator;

public class BreakOut {
    private Paddle _paddle;
    private ArrayList<Ball> _balls=new ArrayList<Ball>();

    private float windowWidth, windowHeight, playZoneWidth, playZoneHeight;
    private ArrayList<Brick> bricks=new ArrayList<Brick>();
    private ArrayList<Bonus> bonus=new ArrayList<Bonus>();
    private int bricksCols=10, bricksRows=15, brickWidth=64, brickHeight=32;

    private ShapeRenderer shapeRenderer;

    /*
    @TODO
    - ajouter une vitesse pour le paddle (en fonction de la souris)
    - gestion des bonus, tirs
     */

    public BreakOut() {
        windowWidth=Gdx.graphics.getWidth();
        windowHeight=Gdx.graphics.getHeight();
        playZoneWidth=bricksCols*brickWidth;
        playZoneHeight=windowHeight;
    }

    public void init() {
        _paddle=new Paddle(new Vector2(100, 50));
        _balls.add(new Ball(new Vector2(400, 300), new Vector2(-2f, -2f)));
        _balls.add(new Ball(new Vector2(400, 300), new Vector2(3f, 3.5f)));
        createBrick();
    }

    private void createBrick() {
        for (int cols=0; cols<bricksCols; cols++) {
            Brick brick=new Brick("green", new Vector2(64, playZoneHeight-brickHeight*cols));
            bricks.add(brick);
        }

        for (int rows=0; rows<bricksRows; rows++) {
            Brick brick=new Brick("yellow", new Vector2(256, playZoneHeight-brickHeight*rows));
            bricks.add(brick);

        }
    }

    public void paddleCollision() {
        _paddle.wallCollision(playZoneWidth, playZoneHeight);
    }

    private void checkBalls() {
        if (_balls.size() == 0)  {
            Tools.debug("fin de la partie");
        }
    }

    private void ballsCollision() {
        Iterator itr = _balls.iterator();
        while (itr.hasNext()) {
            Ball _ball = (Ball)itr.next();
            ballCollision(_ball);
            _ball.setPositionBySpeed();

            if (_ball.isDestroyed()) {
                itr.remove();
            }
        }
    }

    private void ballCollision(Ball _ball) {
        if (_ball.wallCollision(playZoneWidth, playZoneHeight)) {
            return ;
        }

        if (_ball.paddleCollision(_paddle.getBoundingBox())) {
        //if (_ball.elementCollision(paddleBoundingBox)) {

            return ;
        }

        Iterator itr = bricks.iterator();
        while (itr.hasNext()) {
            Brick brick = (Brick)itr.next();

            if (_ball.elementCollision(brick.getBoundingBox())) {
                brick.decreaseHealth();
                if (brick.isDestroyed()) {
                    if (brick.showBonus()) {
                        bonus.add(new Bonus(brick.getColor(), brick._position, 3));
                    }
                    itr.remove();
                }

                break;
            }
        }
    }

    private void bonusCollision() {
        Iterator itr = bonus.iterator();
        while (itr.hasNext()) {
            Bonus b=(Bonus)itr.next();

            if (b.paddleCollision(_paddle.getBoundingBox())) {
                Tools.debug("bonus obtenu");
                itr.remove();
            }

            b.setPositionBySpeed();
        }
    }

    public void updateBoundingBox() {
        checkBalls();
        _paddle.updateBoundingBox();
        for (Ball _ball : _balls) {
            _ball.updateBoundingBox();
        }

        for (Bonus b : bonus) {
            b.updateBoundingBox();
        }
    }

    public void update(float delta) {
        updateBoundingBox();
        ballsCollision();
        paddleCollision();
        bonusCollision();
    }

    public void render(SpriteBatch batch) {
        _paddle.render(batch);
        for (Ball _ball : _balls) {
            _ball.render(batch);
        }

        for(Brick brick : bricks) {
            brick.render(batch);
        }

        for (Bonus b : bonus) {
            b.render(batch);
        }
    }

    public void setPositionX(int x) {
        if ((x-_paddle.getWidth()/2 > -5) && (x+_paddle.getWidth()/2 < playZoneWidth+5)) {
            _paddle.setPosition(x - _paddle.getWidth() / 2, _paddle.getY());
        }
    }

}
