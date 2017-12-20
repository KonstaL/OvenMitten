package fi.konstal.engine.gameobject;

import fi.konstal.engine.util.Sprite;
import fi.konstal.engine.util.SpriteImage;
import javafx.scene.image.Image;

/**
 * Decoration is meant to be a GameObject with a sprite but without a collider
 * <p>
 * Used mainly for decorating the map and game
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public class Decoration extends GameObject {
    private transient Sprite sprite;

    /**
     * Instantiates a new Decoration.
     *
     * default Sprite will be a picture of a Oven Mitten
     *
     * @param x      the x-coordinate
     * @param y      the y -coordinate
     * @param width  the width
     * @param height the height
     */
    public Decoration(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.sprite = new SpriteImage("OvenMitten.jpeg");
    }


    /**
     * Instantiates a new Decoration.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     */
    public Decoration(int x, int y, int width, int height, Sprite sprite) {
        super(x, y, width, height);
        this.sprite = sprite;
    }

    /**
     * Does nothing
     */
    @Override
    public void init() {
        //Do nothing
    }

    /**
     * Does nothing
     */
    @Override
    public void update() {
        //Do nothing
    }

    /**
     * Sets the sprite.
     *
     * @param sprite the sprite to be set
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Image getSprite() {
        return sprite.getImage();
    }
}
