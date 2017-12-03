package fi.konstal.engine.util;

import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.map.Map;
import javafx.scene.image.Image;

/**
 * Created by konka on 29.11.2017.
 */
public class Projectile extends GameActor {

    public Projectile(int x, int y, int width, int height, Sprite sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void move(Map map) {

    }


    @Override
    public void getInput() {

    }



}
