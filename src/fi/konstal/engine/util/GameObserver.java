package fi.konstal.engine.util;

/**
 * The custom Observer interface designed to receive StateMessages.
 */
public interface GameObserver {

    /**
     * Receives a update
     *
     * @param o   the observable which it came from
     * @param arg the StateMessage
     */
    void update(GameObservable o, StateMessage arg);
}
