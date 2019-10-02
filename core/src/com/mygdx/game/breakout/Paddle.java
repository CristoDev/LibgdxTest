package com.mygdx.game.breakout;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.utils.Observer;
import com.mygdx.game.utils.ObserverManager;
import com.mygdx.game.utils.Tools;

public class Paddle extends BreakElement implements Observer {
    private Vector2 speed0=new Vector2(0, 0);
    private float speedMaxX=8f;

    public Paddle(Vector2 position) {
        _height=1;
        init("paddleBlu", position, speed0, Movement.MOBILE);
        ObserverManager.getInstance().register(Observer.ObserverEvent.KEY_UP, this);
        ObserverManager.getInstance().register(Observer.ObserverEvent.KEY_DOWN, this);
    }

    @Override
    public void onNotify(ObserverEvent event, String message) {
        if (event.compareTo(ObserverEvent.KEY_UP) == 0) {
            keyPressed(Integer.parseInt(message), 0);
        }
        else if (event.compareTo(ObserverEvent.KEY_DOWN) == 0) {
            keyPressed(Integer.parseInt(message), speedMaxX);
        }
    }

    private void keyPressed(int keycode, float speedX) {
        switch (keycode) {
            case Input.Keys.LEFT:
                _speed.x=speedX*-1;
                break;
            case Input.Keys.RIGHT:
                _speed.x=speedX;
                break;
            default:
        }
    }

    public boolean wallCollision(float windowWidth, float windowHeight) {
        boolean result=false;
        if (_sprite.getX() < 0) {
            _speed.x=0;
            _sprite.setX(0);
            result=true;
        }
        else if (_sprite.getX() + _width > windowWidth) {
            _speed.x=0;
            _sprite.setX(windowWidth-_width);
            result=true;
        }

        return result;
    }
}
