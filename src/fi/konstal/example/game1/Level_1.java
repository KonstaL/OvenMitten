package fi.konstal.example.game1;

import fi.konstal.engine.core.Level;
import fi.konstal.engine.gameobject.Decoration;
import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Sprite;
import fi.konstal.engine.util.SpriteAnimation;
import fi.konstal.example.game1.util.Hero;
import fi.konstal.example.game1.util.Trump;

import java.util.ArrayList;
import java.util.List;

public class Level_1 implements Level {
    private List<? super GameObject> gameObjects;
    private Map map;

    public Level_1(Map map) {
        this.map = map;
        this.gameObjects = new ArrayList<>();
        init();
    }

    public void init() {

        //int OFFSET_X =  18; //to implement
        //int OFFSET_Y =  25;


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
}
