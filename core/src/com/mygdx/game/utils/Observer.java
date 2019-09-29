package com.mygdx.game.utils;

public interface Observer {
    public static enum ObserverEvent {
        KEY_UP,
        KEY_DOWN
    }

    public void onNotify(ObserverEvent event, String message);

    // @TODO ajouter cette interface aux classes en ayant besoin (controle paddle et gestion touches du clavier)
}
