package fi.konstal.engine.gameobject.collider;

public class Ellipse extends javafx.scene.shape.Ellipse implements Collider {
    @Override
    public void update(int x, int y) {
        setCenterX(x);
        setCenterY(y);
    }
}
