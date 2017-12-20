package fi.konstal.example.game2.util;


import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.gameobject.collider.Polygon;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Fileloader;
import fi.konstal.engine.util.LevelTemplate;
import fi.konstal.engine.util.SpriteImage;
import fi.konstal.example.game2.EnemyCarrier;
import fi.konstal.example.game2.SpaceShip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;


import java.util.ArrayList;
import java.util.List;

public class Level_1 extends LevelTemplate {
    public Level_1() {
        setGameObjects(new ArrayList<>());
        loadAssets();
        init();
    }

    public void init() {
        SpaceShip ship = new SpaceShip(355, 700, 50, 80, new SpriteImage("ship.png"), 50);
        ship.setCollider(new Polygon(
                ship.getX(),
                ship.getY(),
                13.0,
                30.0,
                20.0,
                0.0,
                30.0,
                0.0,
                35.0,
                20.0,
                ship.getWidth(),
                40.0,
                ship.getWidth(),
                ship.getHeight(),
                0.0,
                ship.getHeight(),
                0.0,
                40.0));

        getGameObjects().add(ship);

        for(int i = 0; i < 3; i++) {
            for(int y = 0; y < 1; y++) {
                EnemyCarrier carrier = new EnemyCarrier(300*i, 100*y, 70, 70, new SpriteImage("carrier.png"), 20);
                getGameObjects().add(carrier);
            }
        }
    }

    @Override
    public void loadAssets() {
        CanvasMap cv = new CanvasMap(700, 1000, Color.ROYALBLUE);
        cv.setLineAmount(70);

        //sets the map
        setMap(cv);


        try {
            Media pick = new Media(Level_1.class.getResource("/deepSpace.mp3").toURI().toString());
            setBgm(new MediaPlayer(pick));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
