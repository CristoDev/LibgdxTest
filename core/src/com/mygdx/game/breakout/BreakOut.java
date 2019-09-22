package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class BreakOut {
    private static final String ATLAS="breakout/breakout.pack";

    private Sprite paddle, ball;
    private Vector2 paddlePosition=new Vector2(100, 50);
    private Vector2 ballPosition=new Vector2(400, 300);
    private Vector2 ballSpeed=new Vector2(-2f, -2f);
    private float paddleWidth, paddleHeight, windowWidth, windowHeight, ballWidth, ballHeight, ballRadius;
    private Rectangle paddleBoundingBox=new Rectangle();
    private Rectangle ballBoundingBox=new Rectangle();
    private TextureAtlas atlas;
    private ArrayList<Brick> bricks=new ArrayList<Brick>();

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
        TextureRegion texture=atlas.findRegion("ballGrey");
        ball=new Sprite(texture);
        ballWidth=texture.getRegionWidth();
        ballHeight=texture.getRegionHeight();
        ballRadius=ballWidth/2;

        ball.setPosition(ballPosition.x, ballPosition.y);
    }

    private void createBrick() {
        for (int i=0; i<5; i++) {
            // @todo modifier la position pour prendre un tableau (le reste doit etre fait dans la classe brick)
            Brick brick=new Brick("green", new Vector2(64*(i+1), 500));
            bricks.add(brick);
        }
    }

    private void ballCollisionX() {
        ballSpeed.x*=-1;
    }

    private void ballCollisionY() {
        ballSpeed.y*=-1;
    }



    private void ballCollision() {
        if (ball.getX() < 0 || ball.getX()+ballWidth > windowWidth) {
            ballCollisionX();
        }

        if (ball.getY() < 0 || ball.getY()+ballHeight > windowHeight) {
            ballCollisionY();
        }

        if (ballBoundingBox.overlaps(paddleBoundingBox)) {
            Tools.debug("collision");

            Tools.debug(ballBoundingBox.x+"/"+ballBoundingBox.y+" -> "+(ballBoundingBox.x+ballBoundingBox.width)+"/"+(ballBoundingBox.y+ballBoundingBox.height));
            Tools.debug(paddleBoundingBox.x+"/"+paddleBoundingBox.y+" -> "+(paddleBoundingBox.x+paddleBoundingBox.width)+"/"+(paddleBoundingBox.y+paddleBoundingBox.height));

            /*
            if (paddleBoundingBox.contains(new Vector2(ball.getX(), ball.getY()))) {
                Tools.debug("collsion sur X");
                ballCollisionX();
            }
            else {
                Tools.debug("collsion sur Y ------");
                ballCollisionY();
            }
*/
            ballCollisionY();

        }

        Iterator itr = bricks.iterator();
        while (itr.hasNext()) {
            Brick brick = (Brick)itr.next();
            if (ballBoundingBox.overlaps(brick.getBoundingBox())) {
                Tools.debug("collision avec une brique --- changement de direction à faire");
                itr.remove();
                break;
            }
        }


        /*
        by+vy < paddle y --> collision y

        bx+vx < paddle x --> collision x
        ...

         */
        /*
        for (int i = 0; i < Commons.N_OF_BRICKS; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                var pointLeft = new Point(ballLeft - 1, ballTop);
                var pointTop = new Point(ballLeft, ballTop - 1);
                var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {

                    if (bricks[i].getRect().contains(pointRight)) {

                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {

                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {

                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {

                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                }
            }
        }


         */
    }

    public void updateBoundingBox() {
        paddleBoundingBox.set(paddle.getX(), paddle.getY(), paddleWidth, paddleHeight);
        //ballBoundingBox.set(ball.getX()+ballSpeed.x, ball.getY()+ballSpeed.y, ballWidth, ballHeight);
        ballBoundingBox.set(ball.getX(), ball.getY(), ballWidth, ballHeight);
    }

    public void update(float delta) {
        updateBoundingBox();
        ballCollision();
        ball.setPosition(ball.getX()+ballSpeed.x, ball.getY()+ballSpeed.y);

    }

    public void render(SpriteBatch batch) {
        paddle.draw(batch);
        ball.draw(batch);

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
