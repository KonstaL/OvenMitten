package fi.konstal.example.game2.util;


import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.Projectile;
import fi.konstal.engine.util.Sprite;

import java.io.Serializable;
import java.util.List;

/**
 * The type Missile.
 *
 * Uhm, I actually ran outta time javadockin, so the rest this autogenerated.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Missile extends Projectile implements Serializable {
    /**
     * Instantiates a new Missile.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     * @param damage the damage
     */
    public Missile(int x, int y, int width, int height, Sprite sprite, int hp, int damage) {
        super(x, y, width, height, sprite, hp, damage);
    }

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

    @Override
    public void update() {
        if (getY() < -50 || getY() > 1000) {
            setAlive(false);
        }
    }

    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        setX(getX()+getxVelocity());
        setY(getY()+getyVelocity());
        getCollider().update(getX(), getY());
    }
}
