package fi.konstal.engine.util;


import javafx.scene.image.Image;

import java.io.InputStream;

public class SpriteImage extends Image implements Sprite {
    public SpriteImage(String url) {
        super(url);
    }

    public SpriteImage(InputStream is) {
        super(is);
    }

    public SpriteImage() {
        super("OvenMitten.jpeg");
    }


    @Override
    public Image getImage() {
        return this;
    }
}
