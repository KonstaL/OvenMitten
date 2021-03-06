package fi.konstal.example.game2;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.gameobject.Projectile;
import fi.konstal.engine.sprite.Sprite;
import fi.konstal.engine.sprite.SpriteAnimation;
import fi.konstal.example.game2.util.Missile;

import java.io.Serializable;
import java.util.List;

/**
 * The main enemy of Game2.
 *
 * Slowly moves down while firing Missiles
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class EnemyCarrier extends GameActor implements Serializable {
    private int counter;
    private Sprite missileSprite = new SpriteAnimation(
            "enemyMissile.png",
                    1,
                    5,
                    60,
                    112,
                    0,
                    0,
                    5
    );

    /**
     * Instantiates a new Enemy carrier.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     */
    public EnemyCarrier(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        //Prevent from going over screen
        if (getX() + getxVelocity()+ getWidth() > map.getWidth() || getX() + getxVelocity() < 0) {
            setxVelocity(0);
        }

        //Update position and collider
        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleCollision(Zone c) {
        if(c instanceof Projectile) {
            setHp(getHp() - ((Projectile) c).getDamage());
        }

        if(getHp() <= 0) {
            setAlive(false);
        }
    }

    /**
     * Fire a Missile that goes downwards.
     */
    public void fireProjectile() {
        Missile temp = new Missile(
                getX() + getWidth()/2 -20,
                getY() -50,
                40,
                50,
                missileSprite,
                10,
                10
        );
        temp.setyVelocity(5);
        temp.setParent(this.getClass());
        GameLoop.addGameObject(temp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if(counter > 25) {
            setxVelocity((int)(Math.random()*40)-20);
            setyVelocity((int)(Math.random()*6)+1);

            if((int)(Math.random()*10) > 7) {
                fireProjectile();
            }
            counter = 0;
        } else {
            setxVelocity(0);
            setyVelocity(0);
            counter++;
        }
    }
}
