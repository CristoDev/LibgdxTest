package com.mygdx.game.breakout;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Observer;
import com.mygdx.game.utils.ObserverManager;

public class Ball extends BreakElement implements Observer {
    protected float ballRadius=1f;

    public Ball(Vector2 position) {
        init("ballGrey", position, new Vector2(MathUtils.random(-3f, 3f), MathUtils.random(2f, 5f)), Movement.MOBILE);
    }

    protected void init(String region, Vector2 position, Vector2 speed, Movement movement) {
        ObserverManager.getInstance().register(ObserverEvent.KEY_UP, this);
        if (Math.abs(speed.x) < 1) {
            speed.x = Math.signum(speed.x);
        }

        ballRadius=_width/2;

        super.init(region, position, new Vector2(0, 0), speed, movement);
    }

    @Override
    public void onNotify(ObserverEvent event, String message) {
        if (event.compareTo(ObserverEvent.KEY_UP) == 0) {
            keyPressed(Integer.parseInt(message));
        }
    }

    private void keyPressed(int keycode) {
        switch (keycode) {
            case Input.Keys.SPACE:
                if (_speed.x == 0 || _speed.y == 0) {
                    _speed = _speed0;
                }
                break;
            default:
        }
    }
}
