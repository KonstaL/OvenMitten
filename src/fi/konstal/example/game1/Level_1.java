package fi.konstal.example.game1;

import fi.konstal.engine.core.Level;
import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.*;
import fi.konstal.example.game1.util.*;

import javafx.scene.media.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Level_1 implements Level {
    private List<? super GameObject> gameObjects;
    private Map map;
    private MediaPlayer bgm;

    public Level_1(Map map) {
        this.map = map;
        this.gameObjects = new ArrayList<>();
        init();
    }

    public void init() {

        try {
            Media pick = new Media(this.getClass().getResource("/bgm.mp3").toURI().toString());
            bgm = new MediaPlayer(pick);
        } catch (MediaException | URISyntaxException e) {
            System.out.println("Error while getting media in " + this.getClass());
            e.printStackTrace();
        }


        Sprite sp0 = new SpriteAnimation("Sprite_walk.png", 1, 5, 32, 64, 32, 0,  8);
        Sprite sp1 = new SpriteAnimation("Sprite_walk.png", 1, 5, 32, 64, 32, 64,  8);
        Sprite sp2 = new SpriteAnimation("Sprite_walk.png", 1, 5, 32, 64, 32, 128,  8);
        Sprite sp3 = new SpriteAnimation("Sprite_walk.png", 1, 5, 32, 64, 32, 192,  8);
        Sprite sp4 = new SpriteAnimation("Sprite_walk.png", 1, 1, 32, 64, 0, 0,  8);
        Sprite sp5 = new SpriteAnimation("Sprite_walk.png", 1, 1, 32, 64, 0, 64,  8);
        Sprite sp6 = new SpriteAnimation("Sprite_walk.png", 1, 1, 32, 64, 0, 128,  8);
        Sprite sp7 = new SpriteAnimation("Sprite_walk.png", 1, 1, 32, 64, 0, 192,  8);

        Hero hero = new Hero(700,500,40, 70, sp1, 30);
        hero.addSprites(sp0, sp1, sp2, sp3, sp4, sp5, sp6, sp7);

        Sprite trSp0 = new SpriteAnimation("trump_run.png", 1, 6, 256, 256, 0, 0, 5);
        Sprite trSp1 = new SpriteAnimation("trump_run.png", 1, 6, 256, 256, 0, 256, 5);
        Sprite trSp2 = new SpriteAnimation("trump_run.png", 1, 6, 256, 256, 0, 512, 5);
        Sprite trSp3 = new SpriteAnimation("trump_run.png", 1, 6, 256, 256, 0, 768, 5);


        Trump trump = new Trump(400, 600, 120, 120, trSp0, 40);
        trump.setTarget(hero);
        trump.addSprites(trSp0, trSp1, trSp2, trSp3);

        Decoration mitten = new Decoration(500, 1500, 100, 50); //demonstrates default sprite

        gameObjects.add(hero);
        gameObjects.add(trump);
        gameObjects.add(mitten);
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
