package fi.konstal.engine.gameobject.collider;

/**
 * A rectangle shaped Collider
 *
 * If no collider is specified in a GameActor, Collider defaults to the Rectangle collider
 */
public class Rectangle extends javafx.scene.shape.Rectangle implements Collider {

    /**
     * Instantiates a new Rectangle.
     *
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int x, int y) {
        setX(x);
        setY(y);
    }
}
