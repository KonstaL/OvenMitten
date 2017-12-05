package fi.konstal.engine.util;

import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.map.Map;
import javafx.scene.image.Image;

/**
 * Created by konka on 29.11.2017.
 */
public abstract class Projectile extends GameActor {
    int damage;

    public Projectile(int x, int y, int width, int height, Sprite sprite, int hp, int damage) {
        super(x, y, width, height, sprite, hp);
        this.damage = damage;
    }

    @Override
    public void move(Map map) {
        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
