package fi.konstal.engine.map;

import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by konka on 28.11.2017.
 */
public interface Map {
    void draw(GraphicsContext gc, Camera c);


}
