package fi.konstal.example.game2;


import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.Projectile;
import fi.konstal.engine.util.Sprite;

import java.util.Iterator;
import java.util.List;

public class Missile extends Projectile {
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
            //GameLoop.removeProjectile(this);
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
