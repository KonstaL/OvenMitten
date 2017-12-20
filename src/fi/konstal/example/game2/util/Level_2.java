package fi.konstal.example.game2.util;


import fi.konstal.engine.util.LevelTemplate;
import fi.konstal.engine.util.SpriteImage;
import fi.konstal.example.game2.EnemyCarrier;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;


import java.util.ArrayList;


public class Level_2 extends LevelTemplate {
    public Level_2() {
        setGameObjects(new ArrayList<>());
        loadAssets();
        init();
    }

    public void init() {
        //Generate enemies
        for(int i = 0; i < 3; i++) {
            for(int y = 0; y < 3; y++) {
                EnemyCarrier carrier = new EnemyCarrier(300*i, 100*y, 70, 70, new SpriteImage("carrier.png"), 20);
                getGameObjects().add(carrier);

            }
        }
    }

    @Override
    public void loadAssets() {
        CanvasMap cv = new CanvasMap(700, 1000, Color.WHITE);
        cv.setLineAmount(100);

        setMap(cv);

        try {
            Media pick = new Media(Level_2.class.getResource("/deepSpace.mp3").toURI().toString());
            setBgm(new MediaPlayer(pick));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
