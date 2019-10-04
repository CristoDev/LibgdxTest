package com.mygdx.game.utils;

import java.util.ArrayList;
import java.util.HashMap;

public class ObserverManager {
    private HashMap<Observer.ObserverEvent, ArrayList<Observer>> _observers=new HashMap<Observer.ObserverEvent, ArrayList<Observer>>();
    private static ObserverManager _manager=null;

    private ObserverManager() {
    }

    public static ObserverManager getInstance() {
        if (_manager == null) {
            _manager=new ObserverManager();
        }

        return _manager;
    }

    public void register(Observer.ObserverEvent event, Observer observer) {
        if (_observers.get(event) == null) {
            _observers.put(event, new ArrayList<>());
        }

        _observers.get(event).add(observer);
    }

    public void unregister(Observer.ObserverEvent event, Observer observer) {
        if (_observers.get(event) == null) {
            return ;
        }

        _observers.get(event).remove(observer);
    }

    public void notify(Observer.ObserverEvent event, String message) {
        if (_observers.get(event) == null) {
            Tools.debug("NOTIFY", "personne pour "+event.name());
            return ;
        }

        for (Observer observer : _observers.get(event)) {
            observer.onNotify(event, message);
        }
    }

}