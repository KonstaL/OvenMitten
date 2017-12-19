package fi.konstal.engine.util;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.InputStream;

public class SpriteImage implements Sprite {
    private Image sprite;
    public SpriteImage(String filename) {
        sprite = new Image(filename);
    }


    public SpriteImage() {
        sprite = new Image("OvenMitten.jpeg");
    }


    @Override
    public Image getImage() {
        return sprite;
    }


}
