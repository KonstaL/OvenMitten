package fi.konstal.engine.gameobject.collider;

import java.io.Serializable;

/**
 * A circle shaped collider
 *
 * @author Konsta Lehtinen
 * @version 2017 -20-12
 */
public class Circle extends javafx.scene.shape.Circle implements Collider, Serializable {

    /**
     * Instantiates a new Circle.
     *
     * @param centerX the center x-coordinate
     * @param centerY the center y-coordinate
     * @param radius  the radius of the circle
     */
    public Circle(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int x, int y) {
        setCenterX(x);
        setCenterX(y);
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
