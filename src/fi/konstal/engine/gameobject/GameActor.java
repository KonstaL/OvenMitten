package fi.konstal.engine.gameobject;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;


/**
 * Created by konka on 15.11.2017.
 */
public abstract class GameActor extends Zone {
    private int hp;
    private boolean isAlive;
    private int xVelocity;
    private int yVelocity;
    private Sprite sprite;
    private KeyboardInput keyboardInputListener;
    private Enum<Direction> direction;


    public GameActor(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height);
        this.hp = hp;
        this.sprite = sprite;
        this.isAlive = true;
    }

    public GameActor(int x, int y, int width, int height, int hp) {
        super(x, y, width, height);
        this.hp = hp;
        this.sprite = new SpriteImage();
        this.isAlive = true;
    }


    abstract public void move(Map map);
    abstract public void getInput();




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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}
