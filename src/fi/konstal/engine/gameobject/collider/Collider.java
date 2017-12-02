package fi.konstal.engine.gameobject.collider;

import javafx.geometry.Bounds;

public interface Collider {
    boolean intersects(Bounds localbounds);
}
