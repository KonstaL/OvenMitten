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
 * The second level of game2
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Level_2 extends LevelTemplate {

    /**
     * Instantiates a new Level 2.
     */
    public Level_2() {
        setGameObjects(new ArrayList<>());
        init();
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
        //Generate enemies
        for(int i = 0; i < 3; i++) {
            for(int y = 0; y < 3; y++) {
                EnemyCarrier carrier = new EnemyCarrier(300*i, 100*y, 70, 70, new SpriteImage("carrierImage", "carrier.png"), 20);
                getGameObjects().add(carrier);

            }
        }

        CanvasMap cv = new CanvasMap(700, 1000, Color.WHITE);
        cv.setLineAmount(100);

        setMap(Util.getUniqueID(), cv);

        try {
            Media pick = new Media(Level_2.class.getResource("/deepSpace.mp3").toURI().toString());
            setBgm(1, new MediaPlayer(pick));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
