package fi.konstal.engine.gameobject.collider;

public class Circle extends javafx.scene.shape.Circle implements Collider {

    public Circle(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
    }


    @Override
    public void update(int x, int y) {
        setCenterX(x);
        setCenterX(y);
    }
}
