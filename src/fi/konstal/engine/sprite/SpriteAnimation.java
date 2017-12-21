package fi.konstal.engine.sprite;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class SpriteAnimation extends Animation implements Sprite, Serializable {
    private ImageView sheet;
    private ArrayList<Image> images;
    private Image currentFrame;
    private int cycleDuration;
    private int counter;


    /**
     * Instantiates a new Animation.
     *
     * @param path          the path of the Image file
     * @param rows          the amount of rows
     * @param amount        the amount of images per row
     * @param width         the width of a single image
     * @param height        the height of a single image
     * @param xOffset       the x offset
     * @param yOffset       the y offset
     * @param cycleDuration The frameDuration of a single image
     */
    public SpriteAnimation(String filename, int rows, int amount, int width, int height, int xOffset, int yOffset, int cycleDuration) {
        images = new ArrayList<>();
        counter = 0;


        this.cycleDuration = cycleDuration;

        parseSheet(filename, f xOffset, yOffset);
    }


    public void parseSheet(String filename,int rows, int perRow, int width, int height, int xOffset, int yOffset) {
        BufferedImage img = null;

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename)) {
            img = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Could not load sprite sheet image: " + filename);
            e.printStackTrace();
        }

        if(img != null) {
            for(int i = 0; i < rows; i++) {
                for (int y = 0; y < amount; y++) {
                    images.add( SwingFXUtils.toFXImage(img.getSubimage(xOffset + (width * y) ,yOffset + (height * i), width, height), null));
                }
            }
            currentFrame = images.get(0);
            System.out.println("Animointi spritesheet tehty!");
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
        if(counter >= cycleDuration) {
            if(images.size()-1  <= images.indexOf(currentFrame)) {
                currentFrame = images.get(0);
            } else {
                currentFrame = images.get(images.indexOf(currentFrame) + 1);
            }
            counter = 0;
        }
        counter++;
        return currentFrame;
    }

    @Override
    public Image getImage() {
        return cycleAnimation();
    }

}
