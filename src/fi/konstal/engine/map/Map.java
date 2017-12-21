package fi.konstal.engine.map;

import fi.konstal.engine.camera.Camera;
import javafx.scene.canvas.GraphicsContext;

/**
 * An interface for handling the game map
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public interface Map {
    /**
     * Draws the map to the given graphicscontext with camera offset
     *
     * @param gc the graphicsContext
     * @param c  the camera
     */
    void draw(GraphicsContext gc, Camera c);

    /**
     * Gets width of the map.
     *
     * @return the width
     */
    double getWidth();

    /**
     * Gets height of the map.
     *
     * @return the height
     */
    double getHeight();
}
