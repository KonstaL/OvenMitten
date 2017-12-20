package fi.konstal.example.game1;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.*;
import fi.konstal.example.game1.util.DirectionState;
import fi.konstal.example.game1.util.Fireball;

import java.util.ArrayList;
import java.util.List;


/**
 * The main player of game 1
 * <p>
 * Extends the normal usability of a GameActor by adding directional spirites and observers
 *
 * @author Konsta Lehtinen
 * @version 20 -12-2017
 */
public class Hero extends GameActor implements GameObservable {

    /**
     * Holds observers
     */
    private List<GameObserver> observers;

    /**
     * Determines if the player is moving
     */
    private boolean isMoving;

    /**
     * list of Sprites which the hero uses
     */
    private List<Sprite> sprites;

    /**
     * Determines the direction in which the player is pointing
     */
    private DirectionState dir;

    /**
     * A sprite from which all projectiles fired from Hero get their sprite
     */
    private Sprite projectileSprite = new SpriteAnimation(
            "fireball.png",
            2,
            6,
            32,
            32,
            0,
            0,
            2
    );

    /**
     * Initializes Hero
     *
     * @param x      x-coordinate on screen
     * @param y      y-coordinate on screen
     * @param width  Width of hero
     * @param height Height of hero
     * @param sprite Sprite of hero
     * @param hp     Starting hitpoints
     */
    public Hero(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
        this.sprites = new ArrayList<>();
        this.observers = new ArrayList<>();
        isMoving = true;
        dir = DirectionState.DOWN;
    }


    /**
     * Updates Hero every frame depending on it's state
     */
    @Override
    public void update() {
        if(getxVelocity() == 0 && getyVelocity() == 0) {
            isMoving = false;
        } else {
            isMoving = true;
        }

        int movement = 0;
        if(!isMoving) {
            movement = 4;
        }

        switch (dir) {
            case DOWN:
                setSprite(sprites.get(0+movement));
                break;
            case UP:
                setSprite(sprites.get(1+movement));
                break;
            case LEFT:
                setSprite(sprites.get(2+movement));
                break;
            case RIGHT:
                setSprite(sprites.get(3+movement));
                break;
        }

        if(getHp() <= 0) {
            setAlive(false);
        }
    }

    /**
     * Moves the Hero every frame
     */
    @Override
    public void move(Map map, List<MapObject> deniedZones) {
            for(MapObject mo: deniedZones) {
                if(goingToCollideX(mo.getCollider())) {
                    setxVelocity(0);
                    System.out.println(mo.getId());
                }
                if(goingToCollideY(mo.getCollider())) {
                    setyVelocity(0);
                }
            }

            setX(getX() + getxVelocity());
            setY(getY() + getyVelocity());
            getCollider().update(getX(), getY());
    }

    /**
     * Returns the current direction
     *
     * @return current DirectionState
     */
    public DirectionState getDirection() {
        return dir;
    }

    /**
     * Sets the direction
     *
     * @param dir the new direction
     */
    public void setDirection(DirectionState dir) {
        this.dir = dir;
    }

    /**
     * Adds sprites to Hero's sprites array
     *
     * @param sprites sprites to add
     */
    public void addSprites(Sprite... sprites) {
        for(Sprite sp : sprites) {
            this.sprites.add(sp);
        }
    }


    @Override
    public void handleCollision(Zone z) {
        if(z instanceof Trump) {
            System.out.println("test");
            setHp(0);
            setAlive(false);
            notifyObservers(StateMessage.LOST);
        }
    }

    /**
     * Fire.
     */
    public void fire() {
        Fireball toFire = new Fireball(
                getX(),
                getY(),
                50,
                50,
                projectileSprite,
                2,
                2);

        switch (dir) {
            case DOWN:
                toFire.setyVelocity(6);
                break;
            case UP:
                toFire.setyVelocity(-6);
                break;
            case LEFT:
                toFire.setxVelocity(-6);
                break;
            case RIGHT:
                toFire.setxVelocity(6);
                break;
        }

        GameLoop.addGameObject(toFire);
    }

    @Override
    public void addObserver(GameObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(GameObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(StateMessage arg) {
        for(GameObserver o : observers) {
            o.update(this, arg);
        }
    }
}


