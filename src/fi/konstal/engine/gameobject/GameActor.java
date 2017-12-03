package fi.konstal.engine.gameobject;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.KeyboardInput;
import fi.konstal.engine.util.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;


/**
 * Created by konka on 15.11.2017.
 */
public abstract class GameActor extends Zone {
    private Image image;
    private int xVelocity;
    private int yVelocity;
    private KeyboardInput keyboardInputListener;
    private Enum<Direction> direction;
    public Animation sp;

    public GameActor(int x, int y, int width, int height, Image image) {
        super(x, y, width, height);
        this.image = image;
    }

    public GameActor(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.image = new Image("OvenMitten.jpeg");
    }


    abstract public void move(Map map);
    abstract public void getInput();



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public KeyboardInput getInputlistener() {
        return keyboardInputListener;
    }

    public void setInputlistener(KeyboardInput keyboardInputListener) {
        this.keyboardInputListener = keyboardInputListener;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }


    public Animation getSp() {
        return sp;
    }

    public void setSp(Animation sp) {
        this.sp = sp;
    }




}

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}
