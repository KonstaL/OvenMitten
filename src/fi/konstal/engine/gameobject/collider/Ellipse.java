package fi.konstal.engine.gameobject.collider;

public class Ellipse extends javafx.scene.shape.Ellipse implements Collider {

    public Ellipse (double centerX, double centerY, double radiusX, double radiusY) {
        super(centerX, centerY, radiusX, radiusY);
    }

    @Override
    public void update(int x, int y) {
        setCenterX(x);
        setCenterY(y);
    }

    @Override
    public double getX() {
        return getCenterX();
    }

    @Override
    public double getY() {
        return getCenterY();
    }
}
