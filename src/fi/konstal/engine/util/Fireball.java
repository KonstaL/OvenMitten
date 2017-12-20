package fi.konstal.engine.util;

import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;

import java.util.List;

public class Fireball extends Projectile {

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
