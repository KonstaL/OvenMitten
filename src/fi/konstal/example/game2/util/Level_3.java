package fi.konstal.example.game2.util;

import fi.konstal.engine.core.Level;
import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.gameobject.collider.Polygon;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Fileloader;
import fi.konstal.engine.util.SpriteImage;
import fi.konstal.example.game2.EnemyCarrier;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Level_3 implements Level {
    private List<? super GameObject> gameObjects;
    private Map map;
    private MediaPlayer bgm;

    public Level_3() {
        this.gameObjects = new ArrayList<>();
        init();
    }

    public void init() {
        CanvasMap cv = new CanvasMap(700, 1000, Color.DARKRED);
        cv.setLineAmount(150);

        this.map = cv;

        try {
            Media pick = new Media(this.getClass().getResource("/deepSpace.mp3").toURI().toString());
            bgm = new MediaPlayer(pick);
        } catch (Exception e) {
            System.out.println(e);
        }


        for(int i = 0; i < 6; i++) {
            for(int y = 0; y < 7; y++) {
                EnemyCarrier carrier = new EnemyCarrier(130*i, 80*y, 50, 50, new SpriteImage("carrier.png"), 20);
                gameObjects.add(carrier);
            }
        }
    }

    @Override
    public List<GameObject> getGameObjects() {
        return (List<GameObject>) gameObjects;
    }

    @Override
    public void setGameObjects(List<GameObject> gameObjects) {

    }

    @Override
    public Map getMap() {
        return map;
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
    }

    @Override
    public MediaPlayer getBgm() {
        return bgm;
    }

    @Override
    public void setBgm(MediaPlayer bgm) {
        this.bgm = bgm;
    }
}
