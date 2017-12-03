package fi.konstal.engine.util;

import javafx.embed.swing.SwingFXUtils;
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
public class Animation {
    private ImageView sheet;
    private ArrayList<Image> images;
    private Image currentFrame;
    private int cycleDuration;
    private int rows;
    private int amount;
    private int width;
    private int height;
    private int counter;


    public Animation(String path, int rows, int amount, int width, int height, int cycleDuration) {
        images = new ArrayList<>();
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
                    images.add( SwingFXUtils.toFXImage(img.getSubimage(width * y , height * i, width, height), null));
                }
            }
            currentFrame = images.get(1);
            System.out.println("Animointi spritesheet tehty!");
        }

    }

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
}
