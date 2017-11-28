package fi.konstal.engine.map;

import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by konka on 28.11.2017.
 */
public class NoMap implements Map {

    @Override
    public void draw(GraphicsContext gc, Camera c) {
        gc.setFill(Color.WHITE);
        gc.fillOval(Math.random()*300 - c.getxOffset(),
                Math.random()*300 - c.getyOffset(),
                Math.random()*5,
                Math.random()*5);
    }
}
