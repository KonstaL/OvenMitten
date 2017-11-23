package fi.konstal.engine;

import fi.konstal.engine.util.KeyboardInput;
import fi.konstal.engine.util.SpriteSheet;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;



/**
 * Created by konka on 15.11.2017.
 */
public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private int xVelocity;
    private int yVelocity;
    private KeyboardInput keyboardInputListener;
    private Enum<Direction> direction;
    private Rectangle2D bounds;
    public SpriteSheet sp;

    public GameObject(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
        this.bounds = new Rectangle2D(x, y, width, height);
    }

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new Image("ship.png");
        this.bounds = new Rectangle2D(x, y, width, height);
    }




    abstract public void move();
    abstract public void getInput();
    abstract public void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

    public Rectangle2D getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle2D bounds) {
        this.bounds = bounds;
    }

    public SpriteSheet getSp() {
        return sp;
    }

    public void setSp(SpriteSheet sp) {
        this.sp = sp;
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}
