package fi.konstal.example.game1;

import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.gameobject.Zone;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.Sprite;
import fi.konstal.example.game1.util.DirectionState;

import java.util.ArrayList;
import java.util.List;

public class Trump extends Enemy {
    private GameObject target;
    private List<Sprite> sprites;
    private DirectionState dir;

    public Trump(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
        this.sprites = new ArrayList<>();
        dir = DirectionState.DOWN;
    }

    public void setTarget(GameObject go) {
        this.target = go;
    }

    @Override
    public void update() {
        switch (dir) {
            case DOWN:
                setSprite(sprites.get(0));
                break;
            case RIGHT:
                setSprite(sprites.get(1));
                break;
            case UP:
                setSprite(sprites.get(2));
                break;
            case LEFT:
                setSprite(sprites.get(3));
                break;
        }
    }

    @Override
    public void move(Map map, List<MapObject> deniedZones) {
        setyVelocity(0);
        setxVelocity(0);

        if(target.getY() + target.getHeight()/2 +1 == getY() + getHeight()/2) {
            setyVelocity(-1);
            dir = DirectionState.UP;
        } else if (target.getY() + target.getHeight()/2 < getY() + getHeight()/2) {
            setyVelocity(-2);
            dir = DirectionState.UP;
        }
        if(target.getY() + target.getHeight()/2 > getY() + getHeight()/2) {
            setyVelocity(2);
            dir = DirectionState.DOWN;
        }
        if(target.getX() + target.getWidth()/2 +1 == getX() + getWidth()/2) {
            setxVelocity(-1);
            dir = DirectionState.LEFT;

        } else if(target.getX() + target.getWidth()/2 < getX() + getWidth()/2) {
            setxVelocity(-2);
            dir = DirectionState.LEFT;
        }
        if(target.getX() + target.getWidth()/2 > getX() + getWidth()/2) {
            setxVelocity(2);
            dir = DirectionState.RIGHT;
        }

        for(MapObject mo: deniedZones) {
            if(goingToCollideX(mo.getCollider())) {
                setxVelocity(0);
            }
            if(goingToCollideY(mo.getCollider())) {
                setyVelocity(0);
            }
        }


        setX(getX()+getxVelocity());
        setY(getY()+getyVelocity());
        getCollider().update(getX(), getY());
    }



    public DirectionState getDirection() {
        return dir;
    }



    public void addSprites(Sprite... sprites) {
        for(Sprite sp : sprites) {
            this.sprites.add(sp);
        }
    }

    @Override
    public void handleCollision(Zone c) {
        System.out.println("collision on enemy class");

        setHp(getHp()-1);

        if(getHp() <= 0) {
            setAlive(false);
        }
    }
}
