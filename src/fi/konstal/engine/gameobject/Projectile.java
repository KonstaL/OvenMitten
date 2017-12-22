package fi.konstal.engine.gameobject;

import fi.konstal.engine.sprite.Sprite;

import java.util.Optional;

/**
 * Created by konka on 29.11.2017.
 *
 * Uhm, I actually ran outta time javadockin, so the rest this autogenerated.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public abstract class Projectile extends GameActor {
    private int damage;
    private Class parent;

    /**
     * Instantiates a new Projectile.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the hp
     * @param damage the damage
     */
    public Projectile(int x, int y, int width, int height, Sprite sprite, int hp, int damage) {
        super(x, y, width, height, sprite, hp);
        this.damage = damage;
        this.parent = null;
    }

    /**
     * Instantiates a new Projectile.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param hp     the hp
     * @param damage the damage
     */
    public Projectile(int x, int y, int width, int height, int hp, int damage) {
        super(x, y, width, height, hp);
        this.damage = damage;
        this.parent = null;
    }

    /**
     * Instantiates a new Projectile.
     */
    public Projectile() {

    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Sets parent.
     *
     * @param c the c
     */
    public void setParent(Class c) {
        this.parent = c;
    }

    /**
     * Gets parent.
     *
     * @return the parent
     */
    public Class getParent() {
        return parent;
    }
}
