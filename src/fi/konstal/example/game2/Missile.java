package fi.konstal.example.game2;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.util.Projectile;
import fi.konstal.engine.util.Sprite;

import java.util.Iterator;

public class Missile extends Projectile {
    public Missile(int x, int y, int width, int height, Sprite sprite, int damage) {
        super(x, y, width, height, sprite, 0, damage);
    }

    @Override
    public void getInput() {

    }

    @Override
    public void onCollision() {
        setAlive(false);
    }

    @Override
    public void update() {
        if (getY() < -50) {
            //GameLoop.removeProjectile(this);
            setAlive(false);
        }
    }
}
