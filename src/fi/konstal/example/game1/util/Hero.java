package fi.konstal.example.game1.util;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.MainPlayer;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.Fireball;
import fi.konstal.engine.util.Sprite;
import fi.konstal.engine.util.SpriteAnimation;
import fi.konstal.engine.util.SpriteImage;

import java.util.ArrayList;
import java.util.List;

public class Hero extends GameActor {
    private boolean isMoving;
    private List<Sprite> sprites;
    private DirectionState dir;
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

    public Hero(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
        this.sprites = new ArrayList<>();
        isMoving = true;
        dir = DirectionState.DOWN;

    }



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

    public DirectionState getDirection() {
        return dir;
    }

    public void setDirection(DirectionState dir) {
        this.dir = dir;
    }

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
        }
    }

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
}


