package fi.konstal.example.game2;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.*;
import fi.konstal.example.game2.util.GameState;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

public class SpaceShip extends GameActor implements GameObservable {
    private List<GameObserver> observers;
    private int counter;
    private boolean isShooting;
    private List<Projectile> projectiles;
    private Sprite missileSprite;

    public SpaceShip(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
        projectiles = new ArrayList<>();
        observers = new ArrayList<>();
        missileSprite = new SpriteAnimation(
                "src/fi/konstal/example/game2/resources/missile.png",
                1,
                5,
                60,
                112,
                5
        );
    }

    @Override
    public void update() {
        //Remove projectiles if they're out of the map
        for(Iterator it = projectiles.iterator(); it.hasNext();) {
            Projectile pr = (Projectile) it.next();

            if (pr.getY() < -50) {
                it.remove();
            }
        }

        //shoot a missile every 6 frames
        if(isShooting && counter > 6) {
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
                40,
                50,
                missileSprite,
                10
        );
        temp.setyVelocity(-5);
        temp.setParent(this.getClass());
        //projectiles.add(temp);
        GameLoop.addGameObject(temp);
    }

    @Override
    public void move(Map map) {
        //If its going out of map in X-axis
        if (getX() + getxVelocity() > map.getWidth() || getX() + getxVelocity() < 0 ||
                getX() + getWidth() + getxVelocity() > map.getWidth() || getX() + getxVelocity() + getWidth() < 0) {
            System.out.println("Yli x!");
            setxVelocity(0);
        }

        //If its going out of map in Y-axis
        if (getY() + getyVelocity() > map.getHeight() || getY() + getyVelocity() < 0 ||
                getY() + getHeight() + getyVelocity() > map.getHeight() || getY() + getyVelocity() + getHeight() < 0) {
            setyVelocity(0);
            System.out.println("Yli y!");
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
            notifyObservers(GameState.LOST);
        }
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
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
    public void notifyObservers(Object arg) {
        for(GameObserver o : observers) {
            o.update(this, arg);
        }
    }
}
