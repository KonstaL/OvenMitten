package fi.konstal.engine.gameobject;

import fi.konstal.engine.GameActor;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Created by e4klehti on 18.11.2017.
 */
public class Enemy extends GameActor {
    public Enemy(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void move() {

    }

    @Override
    public void getInput() {

    }

    @Override
    public void update(Canvas c) {

    }
}