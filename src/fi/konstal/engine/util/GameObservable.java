package fi.konstal.engine.util;

/**
 * A Custom Observable class designed to transport StateMessages
 */
public interface GameObservable {
    /**
     * Add observer.
     *
     * @param o the Observer to add
     */
    void addObserver(GameObserver o);

    /**
     * Remove observer.
     *
     * @param o the Observer to remove
     */
    void removeObserver(GameObserver o);

    /**
     * Notify all observers.
     *
     * @param arg the StateMessage
     */
    void notifyObservers(StateMessage arg);
}
