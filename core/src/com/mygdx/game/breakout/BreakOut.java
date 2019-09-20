package com.mygdx.game.breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

public class BreakOut {
    private static final String ATLAS="breakout/breakout.pack";

    private Sprite paddle, ball;
    private Vector2 paddlePosition=new Vector2(100, 50);
    private Vector2 ballPosition=new Vector2(100, 80);
    private Vector2 ballSpeed=new Vector2(4f, 4f);
    private float paddleWidth, paddleHeight, windowWidth, windowHeight, ballWidth, ballHeight, ballRadius;
    private Rectangle paddleBoundingBox=new Rectangle();
    //private Circle ballBoundingBox=new Circle();
    private Rectangle ballBoundingBox=new Rectangle();
    private TextureAtlas atlas;

    public BreakOut() {
        windowWidth=Gdx.graphics.getWidth();
        windowHeight=Gdx.graphics.getHeight();
    }

    public void init() {
        atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        createPaddle();
        createBall();
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

        }

        /*
        by+vy < paddle y --> collision y

        bx+vx < paddle x --> collision x
        ...

         */

        /*

        collisionRectangle1.set(level.bunnyHead.position.x, level.bunnyHead.position.y,
                level.bunnyHead.bounds.width, level.bunnyHead.bounds.height);
        for (Rock rock : level.rocks) {
            collisionRectangle2.set(rock.position.x, rock.position.y, rock.bounds.width,
                    rock.bounds.height);
            if (!collisionRectangle1.overlaps(collisionRectangle2)) continue;
            onCollisionBunnyHeadWithRock(rock);
        }

         */
    }

    public void updateBoundingBox() {
        paddleBoundingBox.set(paddle.getX(), paddle.getY(), paddleWidth, paddleHeight);
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
    }

    public void setPositionX(int x) {
        if ((x-paddleWidth/2 > -5) && (x+paddleWidth/2 < windowWidth+5)) {
            paddle.setPosition(x - paddleWidth / 2, paddle.getY());
        }
    }
}
