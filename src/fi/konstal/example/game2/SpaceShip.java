package fi.konstal.example.game2;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.Projectile;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.sprite.Sprite;
import fi.konstal.engine.sprite.SpriteAnimation;
import fi.konstal.engine.sprite.SpriteImage;
import fi.konstal.engine.util.*;
import fi.konstal.engine.util.StateMessage;
import fi.konstal.example.game2.util.Missile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Game2's MainPlayer that shoots Missiles
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class SpaceShip extends GameActor implements GameObservable, Serializable {
    private List<GameObserver> observers;
    private int counter;
    private boolean isShooting;
    private Sprite missileSprite;

    /**
     * Instantiates a new Space ship.
     */
    public SpaceShip() {
        super(0,0, 50, 50, new SpriteImage(), 20);
    }

    /**
     * Instantiates a new Space ship.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     */
    public SpaceShip(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);

        observers = new ArrayList<>();
        missileSprite = new SpriteAnimation(
                "missile.png",
                1,
                5,
                60,
                112,
                0,
                0,
                5
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        //shoot a missile every 6 frames
        if(isShooting && counter > 4) {
            fireMissile();
            counter = 0;
        }
        counter++;
    }

    /**
     * Enables the shooting of missiles.
     *
     * @param shooting the shooting
     */
    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    /**
     * Fires a missile.
     */
    public void fireMissile() {
        Missile temp = new Missile(
                getX() + getWidth()/2 -20,
                getY() -50,
                30,
                40,
                missileSprite,
                1,
                4

        );
        temp.setyVelocity(-7);
        temp.setParent(this.getClass());

        //projectiles.add(temp);
        GameLoop.addGameObject(temp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        //If its going out of map in X-axis
        if (getX() + getxVelocity() > map.getWidth() || getX() + getxVelocity() < 0 ||
                getX() + getWidth() + getxVelocity() > map.getWidth() || getX() + getxVelocity() + getWidth() < 0) {
            System.out.println("Over x!");
            setxVelocity(0);
        }

        //If its going out of map in Y-axis
        if (getY() + getyVelocity() > map.getHeight() || getY() + getyVelocity() < 0 ||
                getY() + getHeight() + getyVelocity() > map.getHeight() || getY() + getyVelocity() + getHeight() < 0) {
            setyVelocity(0);
            System.out.println(map.getHeight());
            System.out.println("Over y!");
        }

        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(Zone z) {
        if(z instanceof Projectile)
        setHp(getHp()-((Projectile) z).getDamage());

        if(getHp() <= 0) {
            setAlive(false);
            notifyObservers(StateMessage.LOST);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(GameObserver o) {
        observers.add(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(GameObserver o) {
        observers.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(StateMessage arg) {
        for(GameObserver o : observers) {
            o.update(this, arg);
        }
    }
}
