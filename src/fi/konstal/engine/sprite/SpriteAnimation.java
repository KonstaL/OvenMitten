package fi.konstal.engine.sprite;

import fi.konstal.engine.assetmanager.AssetManager;
import fi.konstal.engine.assetmanager.AssetReferenceNotFoundException;
import fi.konstal.engine.util.Util;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SpriteAnimation implements Sprite, Serializable {
    private int cycleDuration;
    private int size;
    private int frameCounter;
    private int currentIndex;
    private int assetKey;

    public SpriteAnimation(String assetKeyRef, String filename, int rows, int perRow, int width, int height, int xOffset, int yOffset, int cycleDuration) {
        if (AssetManager.containsRef(assetKeyRef)) {
            this.assetKey = AssetManager.getAssetReference(assetKeyRef);
        } else {
            this.currentIndex = 0;
            this.frameCounter = 0;
            this.assetKey = Util.getUniqueID();
            this.cycleDuration = cycleDuration;

            parseSheet(filename, rows, perRow, width, height, xOffset, yOffset);
            AssetManager.addAssetReference(assetKeyRef, assetKey);
        }
    }


    public SpriteAnimation(String filename, int rows, int perRow, int width, int height, int xOffset, int yOffset, int cycleDuration) {
        this.currentIndex = 0;
        this.frameCounter = 0;
        this.assetKey = Util.getUniqueID();
        this.cycleDuration = cycleDuration;

        parseSheet(filename, rows, perRow, width, height, xOffset, yOffset);
    }

    public SpriteAnimation(String assetKeyRef) {
        if (AssetManager.containsRef(assetKeyRef)) {
            this.assetKey = AssetManager.getAssetReference(assetKeyRef);
        } else {
            throw new AssetReferenceNotFoundException("Asset reference was not found!");
        }
    }


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
     * Cycle animation image.
     *
     * @return the current cycles image
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

    @Override
    public Image getImage() {
        return cycleAnimation();
    }

}
