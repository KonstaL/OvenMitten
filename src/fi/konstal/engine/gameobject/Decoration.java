package fi.konstal.engine.gameobject;

import fi.konstal.engine.util.Sprite;
import fi.konstal.engine.util.SpriteImage;
import javafx.scene.image.Image;

/**
 * Created by konka on 29.11.2017.
 */
public class Decoration extends GameObject {
    private Sprite sprite;

    public Decoration(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.sprite = new SpriteImage("OvenMitten.jpeg");
    }

    public Decoration(int x, int y, int width, int height, Sprite sprite) {
        super(x, y, width, height);
        this.sprite = sprite;
    }

    public Image getSprite() {
        return sprite.getImage();
    }
}