package fi.konstal.engine.map;

import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * Created by konka on 28.11.2017.
 */
public class ImageMap implements Map {
    private Image map;

    public ImageMap(Image map) {
        this.map = map;

    }

    public ImageMap(String path) {
        map = new Image(path);

    }
    @Override
    public void draw(GraphicsContext gc, Camera c) {
        gc.drawImage(map,
                -c.getxOffset(),
                -c.getyOffset(),
                map.getWidth(),
                map.getHeight()
        );
    }
}
