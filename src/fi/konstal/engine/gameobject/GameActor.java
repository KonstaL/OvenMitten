package fi.konstal.engine.gameobject;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.sprite.Sprite;
import fi.konstal.engine.sprite.SpriteImage;

import java.util.List;

/**
 * GameActor is a Zone with additional parameters that make it suitable for
 * active GameActors
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public abstract class GameActor extends Zone {
    private int hp;
    private boolean isAlive;
    private int xVelocity;
    private int yVelocity;
    private Sprite sprite;


    /**
     * Instantiates a new Game actor.
     *
     * @param x      the x-coordinates
     * @param y      the y-coordinates
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the initial hitpoints
     */
    public GameActor(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height);
        this.hp = hp;
        this.sprite = sprite;
        this.isAlive = true;
    }

    /**
     * Instantiates a new Game actor.
     *
     * @param x      the x-coordinates
     * @param y      the y-coordinates
     * @param width  the width
     * @param height the height
     * @param hp     the hp
     */
    public GameActor(int x, int y, int width, int height, int hp) {
        super(x, y, width, height);
        this.hp = hp;
        this.sprite = new SpriteImage();
        this.isAlive = true;
    }

    /**
     * Instantiates a new empty Game actor.
     */
    public GameActor() {
        super(0,0, 50, 50);
    }


    /**
     * Move the actor according to current velocity
     *
     * @param map         the map where it's going to move
     * @param deniedZones the Zones that GameActor can't move through
     */
    abstract public void move(Map map, List<MapObject> deniedZones);


    /**
     * Gets X velocity.
     *
     * @return the X velocity
     */
    public int getxVelocity() {
        return xVelocity;
    }

    /**
     * Sets X velocity.
     *
     * @param xVelocity the X velocity
     */
    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    /**
     * Gets Y velocity.
     *
     * @return the Y velocity
     */
    public int getyVelocity() {
        return yVelocity;
    }

    /**
     * Sets Y velocity.
     *
     * @param yVelocity the y velocity
     */
    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Sets sprite.
     *
     * @param sprite the sprite
     */
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Tells whether the GameActor is still alive
     *
     * @return a boolean whether GameActor is alive or not
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets the gameActors alive status.
     *
     * @param alive boolean whether the Actor is alive or dead
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    /**
     * Returns a boolean whether two colliders are about to collide on X-axis.
     *
     * @param c the other collider to check against
     * @return the boolean whether they're going to collide
     */
    public boolean goingToCollideX(Collider c) {
        getCollider().update(getX() + getxVelocity(), getY());

        if (this.collides(c)) {
            return true;
        }
        return false;
    }

    /**
     * Returns a boolean whether two colliders are about to collide on Y-axis.
     *
     * @param c the other collider to check against
     * @return the boolean whether they're going to collide
     */
    public boolean goingToCollideY(Collider c) {
        getCollider().update(getX(), getY()+ getyVelocity());

        if (this.collides(c)) {
            return true;
        }
        return false;
    }
}
