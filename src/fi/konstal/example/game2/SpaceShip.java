package fi.konstal.example.game2;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.*;
import fi.konstal.engine.util.StateMessage;
import fi.konstal.example.game2.util.Missile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceShip extends GameActor implements GameObservable {
    private List<GameObserver> observers;
    private int counter;
    private boolean isShooting;
    private Sprite missileSprite;

    public SpaceShip(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);

        observers = new ArrayList<>();
        missileSprite = new SpriteAnimation(
                "missile.png",
                1,
                5,
                60,
                112,
                0,
                0,
                5
        );
    }

    @Override
    public void update() {
        //shoot a missile every 6 frames
        if(isShooting && counter > 4) {
            fireMissile();
            counter = 0;
        }
        counter++;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public void fireMissile() {
        Missile temp = new Missile(
                getX() + getWidth()/2 -20,
                getY() -50,
                30,
                40,
                missileSprite,
                1,
                4

        );
        temp.setyVelocity(-7);
        temp.setParent(this.getClass());

        //projectiles.add(temp);
        GameLoop.addGameObject(temp);
    }

    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        //If its going out of map in X-axis
        if (getX() + getxVelocity() > map.getWidth() || getX() + getxVelocity() < 0 ||
                getX() + getWidth() + getxVelocity() > map.getWidth() || getX() + getxVelocity() + getWidth() < 0) {
            System.out.println("Over x!");
            setxVelocity(0);
        }

        //If its going out of map in Y-axis
        if (getY() + getyVelocity() > map.getHeight() || getY() + getyVelocity() < 0 ||
                getY() + getHeight() + getyVelocity() > map.getHeight() || getY() + getyVelocity() + getHeight() < 0) {
            setyVelocity(0);
            System.out.println(map.getHeight());
            System.out.println("Over y!");
        }

        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());
    }


    @Override
    public void handleCollision(Zone z) {
        if(z instanceof Projectile)
        setHp(getHp()-((Projectile) z).getDamage());

        if(getHp() <= 0) {
            setAlive(false);
            notifyObservers(StateMessage.LOST);
        }
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
