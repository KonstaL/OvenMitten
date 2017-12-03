package fi.konstal.engine.gameobject;

import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Sprite;
import javafx.scene.image.Image;

/**
 * Created by e4klehti on 18.11.2017.
 */
public class Enemy extends GameActor {
    public Enemy(int x, int y, int width, int height, Sprite sprite) {
        super(x, y, width, height, sprite);
    }
    public Enemy(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void move(Map map) {

    }

    @Override
    public void getInput() {

    }

}
