package fi.konstal.example.game1.util;

import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.gameobject.Projectile;
import fi.konstal.engine.sprite.Sprite;

import java.util.List;

/**
 * The type Fireball.
 *
 * Uhm, I actually ran outta time javadockin, so the rest this autogenerated.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Fireball extends Projectile {

    /**
     * Instantiates a new Fireball.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     * @param damage the damage
     */
    public Fireball(int x, int y, int width, int height, Sprite sprite, int hp, int damage) {
        super(x, y, width, height, sprite, hp, damage);
    }

    @Override
    public void handleCollision(Zone z) {
        if(z instanceof Enemy) {
            this.setAlive(false);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        setX(getX()+getxVelocity());
        setY(getY()+getyVelocity());
        getCollider().update(getX(), getY());

    }
}
