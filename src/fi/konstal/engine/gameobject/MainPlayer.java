package fi.konstal.engine.gameobject;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by e4klehti on 18.11.2017.
 */
    public class MainPlayer extends GameActor {
    public MainPlayer(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    public MainPlayer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void move(Map map) {
        if (getX() + getxVelocity() > map.getWidth() || getX() + getxVelocity() < 0 ||
                getX() + getWidth() + getxVelocity() > map.getWidth() || getX() + getxVelocity() + getWidth() < 0) {
            System.out.println("Yli x!");
            setxVelocity(0);
        }

        if (getY() + getyVelocity() > map.getHeight() || getY() + getyVelocity() < 0 ||
                getY() + getHeight() + getyVelocity() > map.getHeight() || getY() + getyVelocity() + getHeight() < 0) {
            setyVelocity(0);
            System.out.println("Yli y!");
        }

        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());
    }

    @Override
    public void getInput() {
        getInputlistener().update();
    }

}
