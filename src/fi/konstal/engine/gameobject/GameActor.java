package fi.konstal.engine.gameobject;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.*;


import java.util.List;


/**
 * GameActor is a Zone with additional parameters that make it suitable for
 * actice GameActors
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public abstract class GameActor extends Zone {
    private int hp;
    private boolean isAlive;
    private int xVelocity;
    private int yVelocity;
    private transient Sprite sprite;




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

    public GameActor() {
        super(0,0, 50, 50);
    }


    abstract public void move(Map map, List<MapObject> deniedZones);





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

    @Override
    public void init() {
        //do nothing for now
    }

    public boolean goingToCollideX(Collider c) {
        getCollider().update(getX() + getxVelocity(), getY());

        if (this.collides(c)) {
            return true;
        }
        return false;
    }

    public boolean goingToCollideY(Collider c) {
        getCollider().update(getX(), getY()+ getyVelocity());

        if (this.collides(c)) {
            return true;
        }
        return false;
    }
}
