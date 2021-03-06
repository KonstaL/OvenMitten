package fi.konstal.example.game2.util;

import fi.konstal.engine.util.LevelTemplate;
import fi.konstal.engine.sprite.SpriteImage;
import fi.konstal.engine.util.Util;
import fi.konstal.example.game2.EnemyCarrier;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import java.util.ArrayList;

/**
 * The third level of game2.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Level_3 extends LevelTemplate {

    /**
     * Instantiates a new Level 3.
     */
    public Level_3() {
        setGameObjects(new ArrayList<>());
        init();
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
        for(int i = 0; i < 6; i++) {
            for(int y = 0; y < 7; y++) {
                EnemyCarrier carrier = new EnemyCarrier(130*i, 80*y, 50, 50, new SpriteImage("carrierImage", "carrier.png"), 20);
                getGameObjects().add(carrier);
            }
        }

        CanvasMap cv = new CanvasMap(700, 1000, Color.DARKRED);
        cv.setLineAmount(150);

        setMap(Util.getUniqueID(), cv);

        try {
            Media pick = new Media(this.getClass().getResource("/deepSpace.mp3").toURI().toString());
            setBgm(1, new MediaPlayer(pick));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
