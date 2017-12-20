package fi.konstal.engine.map;

import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * ImageMap is a alternative way to implement a map if all you need is a background with no
 * functionality
 */
public class ImageMap implements Map {
    private Image map;

    /**
     * Instantiates a new ImageMap from a Image.
     *
     * @param map The image
     */
    public ImageMap(Image map) {
        this.map = map;
    }

    /**
     * Instantiates a new ImageMap from a path.
     *
     * @param path the path
     */
    public ImageMap(String path) {
        map = new Image(path);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(GraphicsContext gc, Camera c) {
        gc.drawImage(map,
                -c.getXOffset(),
                -c.getYOffset(),
                map.getWidth(),
                map.getHeight()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return map.getWidth();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return map.getHeight();
    }
}
