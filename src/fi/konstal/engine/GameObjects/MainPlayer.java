package fi.konstal.engine.GameObjects;

import fi.konstal.engine.GameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Created by e4klehti on 18.11.2017.
 */
    public class MainPlayer extends GameObject {
    public MainPlayer(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }

    @Override
    public void move() {
        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        setBounds(new Rectangle2D(getX(), getY(), getWidth(), getHeight()));
    }

    @Override
    public void getInput() {
        getInputlistener().update();
    }

    @Override
    public void update() {
        getInput();
        move();
    }

}
