package fi.konstal.engine.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpiteAnimation extends Animation implements Sprite {

    public SpiteAnimation(String path, int rows, int amount, int width, int height, int cycleDuration) {
        super(path, rows, amount, width, height, cycleDuration);
    }

    @Override
    public Image getImage() {
        return cycleAnimation();
    }
}
