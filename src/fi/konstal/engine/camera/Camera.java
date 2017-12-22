package fi.konstal.engine.camera;


/**
 * The Camera interface.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public interface Camera {
    /**
     * Moves the camera
     *
     * @param xAmount the x amount
     * @param yAmount the y amount
     */
    void move(float xAmount, float yAmount);

    /**
     * Gets x offset.
     *
     * @return the x offset
     */
    float getXOffset();

    /**
     * Gets y offset.
     *
     * @return the y offset
     */
    float getYOffset();

    /**
     * Sets x offset.
     *
     * @param xOffset the x offset
     */
    void setXOffset(float xOffset);

    /**
     * Sets y offset.
     *
     * @param yOffset the y offset
     */
    void setYOffset(float yOffset);
}
