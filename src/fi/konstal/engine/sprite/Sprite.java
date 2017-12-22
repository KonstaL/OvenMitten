package fi.konstal.engine.sprite;

import javafx.scene.image.Image;

/**
 * The Sprite interface
 *
 * Used to determine what kind of properties every Sprite needs.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public interface Sprite {

    /**
     * Gets the sprites current image.
     *
     * @return The Image
     */
    Image getImage();
}
