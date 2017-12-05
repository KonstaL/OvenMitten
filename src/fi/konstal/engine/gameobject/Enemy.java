package fi.konstal.engine.gameobject;

import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Sprite;
import javafx.scene.image.Image;

/**
 * Created by e4klehti on 18.11.2017.
 */
public class Enemy extends GameActor {
    public Enemy(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
    }
    public Enemy(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }

    @Override
    public void move(Map map) {

    }

    @Override
    public void update() {

    }

    @Override
    public void getInput() {

    }

    @Override
    public void onCollision() {
        System.out.println("collision on enemy class");

        setHp(getHp()-1);

        if(getHp() <= 0) {
            setAlive(false);
        }
    }
}
