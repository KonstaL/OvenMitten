package fi.konstal.engine.util;

import fi.konstal.engine.GameObjects.Sprite;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by e4klehti on 22.11.2017.
 */
public class SpriteSheet {
    private ImageView sheet;
    private ArrayList<Image> animation;
    private Image currentFrame;
    private int cycleDuration;
    private int rows;
    private int amount;
    private int width;
    private int height;
    private int counter;


    public SpriteSheet(String path, int rows, int amount, int width, int height, int cycleDuration) {
        animation = new ArrayList<>();
        counter = 0;

        this.rows = rows;
        this.amount = amount;
        this.width = width;
        this.height = height;
        this.cycleDuration = cycleDuration;

        parseSheet(path);
    }






    public void parseSheet(String path) {
        File file = new File(path);
        BufferedImage img = null;

        try (FileInputStream fis = new FileInputStream(file)) {
            img = ImageIO.read(fis);
        } catch (IOException e) {
            System.out.println("Could not load sprite sheet image: " + path);
            e.printStackTrace();
        }

        if(img != null) {
            for(int i = 0; i < rows; i++) {
                for (int y = 0; y < amount; y++) {
                    animation.add( SwingFXUtils.toFXImage(img.getSubimage(width * y , height * i, width, height), null));
                }
            }
            currentFrame = animation.get(1);
            System.out.println("DONE!");
        }

    }

    public Image cycleAnimation() {
        if(counter >= cycleDuration) {
            if(animation.size()-1  <= animation.indexOf(currentFrame)) {
                currentFrame = animation.get(0);
            } else {
                currentFrame = animation.get(animation.indexOf(currentFrame) + 1);
            }
            counter = 0;
        }
        counter++;
        return currentFrame;
    }
}
