package fi.konstal.engine.gameobject;


import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.Sprite;


/**
 * Created by e4klehti on 18.11.2017.
 */
    public abstract class MainPlayer extends GameActor {
    public MainPlayer(int x, int y, int width, int height, Sprite sprite, int hp) {
        super(x, y, width, height, sprite, hp);
    }

    public MainPlayer(int x, int y, int width, int height, int hp) {
        super(x, y, width, height, hp);
    }



    @Override
    public void handleCollision(Zone c) {
        System.out.println("MainPlayer collison");
    }

    @Override
    public void update() {

    }
}
