package fi.konstal.example.game2.util;


import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.gameobject.Projectile;
import fi.konstal.engine.sprite.Sprite;

import java.io.Serializable;
import java.util.List;

/**
 * A projectile that is shot by SpaceShip and EnemyCarrier
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Missile extends Projectile implements Serializable {
    /**
     * Instantiates a new Missile.
     *
     * @param x      the starting x-coordinate
     * @param y      the starting y-coordinate
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     * @param damage the damage of the projectile
     */
    public Missile(int x, int y, int width, int height, Sprite sprite, int hp, int damage) {
        super(x, y, width, height, sprite, hp, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(Zone z) {
        if(z instanceof Projectile) {
            setHp(getHp() - ((Projectile)z).getDamage());
        } else {
            setHp(0);
        }
        if(getHp() <= 0) {
            setAlive(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (getY() < -50 || getY() > 1000) {
            setAlive(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        setX(getX()+getxVelocity());
        setY(getY()+getyVelocity());
        getCollider().update(getX(), getY());
    }
}
