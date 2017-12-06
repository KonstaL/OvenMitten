package fi.konstal.engine.util;

public interface GameObservable {
    void addObserver(GameObserver o);
    void removeObserver(GameObserver o);
    void notifyObservers(Object arg);
}
