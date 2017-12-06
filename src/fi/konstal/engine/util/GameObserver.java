package fi.konstal.engine.util;

public interface GameObserver {
    void update(GameObservable o, StateMessage arg);
}
