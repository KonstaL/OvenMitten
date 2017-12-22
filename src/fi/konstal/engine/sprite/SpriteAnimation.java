package fi.konstal.engine.sprite;

import fi.konstal.engine.assetmanager.AssetManager;
import fi.konstal.engine.assetmanager.AssetReferenceNotFoundException;
import fi.konstal.engine.util.Util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * SpriteAnimation is used when you need an animated sprite.
 *
 * It loads the animation from a single image and splits it into sub-images determined by the given parameters
 */
public class SpriteAnimation implements Sprite, Serializable {
    private int cycleDuration;
    private int size;
    private int frameCounter;
    private int currentIndex;
    private int assetKey;

    /**
     * Instantiates a new Sprite animation with a String asset reference
     *
     * If the given String asset key reference is already made, uses it instead.
     *
     * @param assetKeyRef   the String reference
     * @param filename      the filename (where it's going to be loaded)
     * @param rows          the rows
     * @param perRow        the per row image amount
     * @param width         the width of an single image
     * @param height        the height of an single image
     * @param xOffset       the x offset
     * @param yOffset       the y offset
     * @param cycleDuration the frame duration of single image
     */
    public SpriteAnimation(String assetKeyRef, String filename, int rows, int perRow, int width, int height, int xOffset, int yOffset, int cycleDuration) {
        if (AssetManager.containsRef(assetKeyRef)) {
            this.assetKey = AssetManager.getAssetKey(assetKeyRef);
        } else {
            this.currentIndex = 0;
            this.frameCounter = 0;
            this.assetKey = Util.getUniqueID();
            this.cycleDuration = cycleDuration;

            parseSheet(filename, rows, perRow, width, height, xOffset, yOffset);
            AssetManager.addAssetReference(assetKeyRef, assetKey);
        }
    }


    /**
     * Instantiates a new Sprite animation and generates a unique AssetReference.
     *
     * @param filename      the filename
     * @param rows          the rows
     * @param perRow        the per row
     * @param width         the width
     * @param height        the height
     * @param xOffset       the x offset
     * @param yOffset       the y offset
     * @param cycleDuration the cycle duration
     */
    public SpriteAnimation(String filename, int rows, int perRow, int width, int height, int xOffset, int yOffset, int cycleDuration) {
        this.currentIndex = 0;
        this.frameCounter = 0;
        this.assetKey = Util.getUniqueID();
        this.cycleDuration = cycleDuration;

        parseSheet(filename, rows, perRow, width, height, xOffset, yOffset);
    }

    /**
     * Tries to use the argument String asset key reference to find a asset and assign it to this object. if none is found throws an exception
     *
     * @param assetKeyRef the asset key ref
     */
    public SpriteAnimation(String assetKeyRef) {
        if (AssetManager.containsRef(assetKeyRef)) {
            this.assetKey = AssetManager.getAssetKey(assetKeyRef);
        } else {
            throw new AssetReferenceNotFoundException("Asset reference was not found!");
        }
    }


    /**
     * Parses the SpriteSheet by given parameters.
     *
     * @param filename the filename
     * @param rows     the row amount
     * @param perRow   Images per row
     * @param width    the width of a single image
     * @param height   the height of a single image
     * @param xOffset  the x offset
     * @param yOffset  the y offset
     */
    public void parseSheet(String filename,int rows, int perRow, int width, int height, int xOffset, int yOffset) {
        BufferedImage img = null;
        List<Image> images = new ArrayList<>();

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Could not load sprite sheet image: " + filename);
            e.printStackTrace();
        }

        if(img != null) {
            for(int i = 0; i < rows; i++) {
                for (int y = 0; y < perRow; y++) {
                    images.add( SwingFXUtils.toFXImage(img.getSubimage(xOffset + (width * y) ,yOffset + (height * i), width, height), null));
                }
            }
            //add to AssetManager
            this.size = images.size();
            AssetManager.addAsset(assetKey, images);
        } else {
            System.out.println("Could not load sprite sheet image: " + filename);
        }

    }

    /**
     * Cycles the image by measuring the passed frames
     *
     * @return the current image
     */
    public Image cycleAnimation() {
        List<Image> images = (List<Image>) AssetManager.getAssetCollection(assetKey, Image.class);
        if(frameCounter >= cycleDuration) {
            currentIndex++;
            if(size -1  < currentIndex) {
                currentIndex = 0;
            }
            frameCounter = 0;
        }
        frameCounter++;
        return images.get(currentIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getImage() {
        return cycleAnimation();
    }

}
