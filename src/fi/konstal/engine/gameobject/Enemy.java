package fi.konstal.engine.gameobject;

import fi.konstal.engine.sprite.Sprite;


/**
 * Enemy class provides an easy way to group all Enemy GameActors together
 * It itself doesn't do anything
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public abstract class Enemy extends GameActor {
    /**
     * Instantiates a new Enemy.
     *
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param width  the width
     * @param height the height
     * @param sprite the sprite
     * @param hp     the initial hitpoints
     */
    public Enemy(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
    }

    /**
     * Instantiates a new Enemy with the default OvenMitten sprite
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     * @param hp     the initial hitpoints
     */
    public Enemy(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }
}
