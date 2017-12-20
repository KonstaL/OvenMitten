package fi.konstal.engine.gameobject.collider;

import javafx.geometry.Bounds;

/**
 * The Collider interface.
 *
 * Provides an easy to use interface for collision detection
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public interface Collider {

    /**
     * Returns a boolean whether this Collider collides with the given bounds
     *
     * @param localbounds The given bounds
     * @return boolean whether these intersect or not
     */
    boolean intersects(Bounds localbounds);

    /**
     * Updates Colliders base X- and Y-coordinates.
     *
     * @param x the x-coordinate
     * @param y the y-coordinate
     */
    void update(int x, int y);

    /**
     * Gets x-coordinate.
     *
     * @return the x-coordinate
     */
    double getX();

    /**
     * Gets y-coordinate.
     *
     * @return the y-coordinate
     */
    double getY();

    /**
     * Gets the layout bounds.
     *
     * @return the layout bounds
     */
    Bounds getLayoutBounds();
}
