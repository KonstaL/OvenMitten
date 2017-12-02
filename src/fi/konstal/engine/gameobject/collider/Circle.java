package fi.konstal.engine.gameobject.collider;

public class Circle extends javafx.scene.shape.Circle implements Collider {
    @Override
    public void update(int x, int y) {
        setCenterX(x);
        setCenterX(y);
    }
}
