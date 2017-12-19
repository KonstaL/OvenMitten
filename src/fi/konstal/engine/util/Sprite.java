package fi.konstal.engine.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface Sprite {
    Image getImage();
    void setRotate(int rotation);
}
