package com.mygdx.game.utils;

public interface Observer {
    enum ObserverEvent {
        KEY_UP,
        KEY_DOWN
    }

    void onNotify(ObserverEvent event, String message);
}
