package fi.konstal.example.game1.util;

import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.gameobject.Projectile;
import fi.konstal.engine.sprite.Sprite;

import java.util.List;

/**
 * A fireball projectile
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public class Fireball extends Projectile {

    /**
     * Instantiates a new Fireball.
     *
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     * @param damage the damage it makes
     */
    public Fireball(int x, int y, int width, int height, Sprite sprite, int hp, int damage) {
        super(x, y, width, height, sprite, hp, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(Zone z) {
        if(z instanceof Enemy) {
            this.setAlive(false);
        }
    }

    /**
     * Does nothing
     */
    @Override
    public void update() {

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
