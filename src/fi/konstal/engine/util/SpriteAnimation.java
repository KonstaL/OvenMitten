package fi.konstal.engine.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SpriteAnimation extends Animation implements Sprite {

    public SpriteAnimation(String filename, int rows, int amount, int width, int height, int cycleDuration) {
        super(filename, rows, amount, width, height, cycleDuration);
    }

    @Override
    public Image getImage() {
        return cycleAnimation();
    }
}
