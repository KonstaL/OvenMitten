package fi.konstal.engine.gameobject.collider;

public class Rectangle extends javafx.scene.shape.Rectangle implements Collider {
    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public void update(int x, int y) {
        setX(x);
        setY(y);
    }
}
