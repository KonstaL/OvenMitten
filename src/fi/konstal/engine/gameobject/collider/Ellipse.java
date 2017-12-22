package fi.konstal.engine.gameobject.collider;


import java.io.Serializable;

/**
 * An Ellipse shaped Collider.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Ellipse extends javafx.scene.shape.Ellipse implements Collider, Serializable {

    /**
     * Instantiates a new Ellipse.
     *
     * @param centerX the center x-coordinate
     * @param centerY the center y-coordinate
     * @param radiusX the radius of x
     * @param radiusY the radius of y
     */
    public Ellipse (double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int x, int y) {
        setCenterX(x);
        setCenterY(y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return getCenterX();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return getCenterY();
    }
}
