package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Tools;

import java.util.ArrayList;
import java.util.Iterator;

public class BreakOut {
    private Paddle _paddle;
    private ArrayList<Ball> _balls=new ArrayList<Ball>();
    private ArrayList<Shoot> _shoots=new ArrayList<>();

    private float windowWidth, windowHeight, playZoneWidth, playZoneHeight;
    private ArrayList<Brick> bricks=new ArrayList<Brick>();
    private ArrayList<Bonus> bonus=new ArrayList<Bonus>();
    private int bricksCols=10, bricksRows=15, brickWidth=64, brickHeight=32;
    private static Level _level=null;

    public BreakOut() {
        windowWidth=Gdx.graphics.getWidth();
        windowHeight=Gdx.graphics.getHeight();
        playZoneWidth=bricksCols*brickWidth;
        playZoneHeight=windowHeight;
    }

    public void init() {
        _paddle=new Paddle(new Vector2(100, 50));
        _balls.add(new Ball(new Vector2(400, 300)));
        _balls.add(new Ball(new Vector2(400, 300)));
        //createBrick();

        _level=new Level(bricksCols, bricksRows, brickWidth, brickHeight, playZoneHeight);
        createLevel();
    }

    private void createBrick() {
        /*
        for (int cols=0; cols<bricksCols; cols++) {
            Brick brick=new Brick("green", new Vector2(64, playZoneHeight-brickHeight*cols));
            bricks.add(brick);
        }

        for (int rows=0; rows<bricksRows; rows++) {
            Brick brick=new Brick("yellow", new Vector2(256, playZoneHeight-brickHeight*rows));
            bricks.add(brick);

        }
         */

        for (int row=0; row<bricksRows; row++) {
            for (int col=0; col<2; col++) {
                bricks.add(new Brick(new Vector2(col*brickWidth, playZoneHeight-brickHeight*row), 1));
            }
        }

    }

    private void createLevel() {
        _level.nextLevel();
        bricks=_level.createLevel();
    }

    public void paddleCollision() {
        _paddle.wallCollision(playZoneWidth, playZoneHeight);
        _paddle.setPositionBySpeed();
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
                    _ball.setSpeed(1.02f);
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
                _shoots.add(new Shoot(_paddle.getPosition(), 5f));
                itr.remove();
            }
            else if (b.getY() < 0) {
                itr.remove();
            }

            b.setPositionBySpeed();
        }
    }

    private boolean shootCollision(Shoot shoot) {
        Iterator itr = bricks.iterator();
        boolean result=false;

        while (itr.hasNext()) {
            Brick brick = (Brick)itr.next();
            if (shoot.elementCollision(brick.getBoundingBox())) {
                brick.decreaseHealth();
                if (brick.isDestroyed()) {
                    if (brick.showBonus()) {
                        bonus.add(new Bonus(brick.getColor(), brick._position, 3));
                    }
                    itr.remove();
                }

                result=true;
                break;
            }
        }

        return result;
    }

    private void shootsCollision() {
        Iterator itr = _shoots.iterator();
        while (itr.hasNext()) {
            Shoot s=(Shoot)itr.next();

            if (s.getY() > playZoneHeight) {
                itr.remove();
                continue;
            }
            else if (shootCollision(s)) {
                Tools.debug("brique detruite par le tir");
                itr.remove();
                continue;
            }
            else {
                s.setPositionBySpeed();
            }
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

        for (Shoot s : _shoots) {
            s.updateBoundingBox();
        }
    }

    public void update(float delta) {
        updateBoundingBox();
        ballsCollision();
        paddleCollision();
        bonusCollision();
        shootsCollision();
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

        for (Shoot s : _shoots) {
            s.render(batch);
        }
    }

    public void setPositionX(int x) {
        if ((x-_paddle.getWidth()/2 > -5) && (x+_paddle.getWidth()/2 < playZoneWidth+5)) {
            _paddle.setPosition(x-_paddle.getWidth()/2, _paddle.getY());
        }
    }

}
