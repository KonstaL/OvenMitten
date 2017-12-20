package fi.konstal.example.game2.util;

import fi.konstal.engine.util.LevelTemplate;
import fi.konstal.engine.util.SpriteImage;
import fi.konstal.example.game2.EnemyCarrier;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Level_3 extends LevelTemplate {

    public Level_3() {
        setGameObjects(new ArrayList<>());
        loadAssets();
        init();
    }

    public void init() {
        for(int i = 0; i < 6; i++) {
            for(int y = 0; y < 7; y++) {
                EnemyCarrier carrier = new EnemyCarrier(130*i, 80*y, 50, 50, new SpriteImage("carrier.png"), 20);
                getGameObjects().add(carrier);
            }
        }
    }

    public void loadAssets() {
        CanvasMap cv = new CanvasMap(700, 1000, Color.DARKRED);
        cv.setLineAmount(150);

        setMap(cv);

        try {
            Media pick = new Media(this.getClass().getResource("/deepSpace.mp3").toURI().toString());
            setBgm(new MediaPlayer(pick));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
