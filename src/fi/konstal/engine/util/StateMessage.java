package fi.konstal.engine.util;

/**
 * The enum State message.
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public enum StateMessage {

    /**
     * Playing state message.
     */
    PLAYING,

    /**
     * Pause state message.
     */
    PAUSE,

    /**
     * Unpause state message.
     */
    UNPAUSE,

    /**
     * Level cleared state message.
     */
    LEVEL_CLEARED,

    /**
     * Lost state message.
     */
    LOST,

    /**
     * Won state message.
     */
    WON,

    /**
     * Quit state message.
     */
    QUIT;
}
