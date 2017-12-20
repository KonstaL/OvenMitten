package fi.konstal.engine.util;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

public class SpriteAnimation extends Animation implements Sprite, Serializable {

    public SpriteAnimation(String filename, int rows, int amount, int width, int height, int xOffset, int yOffset, int cycleDuration) {
        super(filename, rows, amount, width, height, xOffset, yOffset, cycleDuration);
    }

    public SpriteAnimation(String filename, int rows, int amount, int width, int height, int cycleDuration) {
        super(filename, rows, amount, width, height, 0, 0, cycleDuration);
    }

    public SpriteAnimation() {

    }

    @Override
    public Image getImage() {
        return cycleAnimation();
    }

}
