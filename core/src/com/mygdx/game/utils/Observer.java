package com.mygdx.game.utils;

public interface Observer {
    enum ObserverEvent {
        KEY_UP,
        KEY_DOWN,
        MOVING_X
    }

    void onNotify(ObserverEvent event, String message);
}
