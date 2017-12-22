package fi.konstal.engine.sprite;


import fi.konstal.engine.assetmanager.AssetManager;
import fi.konstal.engine.util.Util;
import javafx.scene.image.Image;

import java.io.Serializable;


/**
 * The SpriteImage is a class used for storing single image sprites.
 * <p>
 * SpriteImage is made to implement the Sprite interface and to provide an assetReference wrapper for
 * JavaFX's own image class
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public class SpriteImage implements Sprite, Serializable {
    private int assetKey;

    /**
     * Instantiates a new Sprite image from an filename and checks for a String reference.
     *
     * if an String reference is already made, uses that. Otherwise stores it own String asset key reference
     *
     * @param assetKeyRef the String asset key reference
     * @param filename    the filename of the image
     */
    public SpriteImage(String assetKeyRef, String filename) {
        if (AssetManager.containsRef(assetKeyRef)) {
            this.assetKey = AssetManager.getAssetKey(assetKeyRef);
        } else {
            this.assetKey = Util.getUniqueID();
            AssetManager.addAssetReference(assetKeyRef, assetKey, new Image(filename));
        }
    }

    /**
     * Instantiates a new Sprite image by a filename.
     *
     * @param filename the filename
     */
    public SpriteImage( String filename) {
        this.assetKey = Util.getUniqueID();
        AssetManager.addAsset(assetKey, new Image(filename));
    }


    /**
     * Instantiates a new default Sprite image.
     */
    public SpriteImage() {
        this.assetKey = Util.getUniqueID();
        AssetManager.addAsset(assetKey, new Image("OvenMitten.jpeg"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
        return AssetManager.getAsset(assetKey, Image.class);
    }
}
