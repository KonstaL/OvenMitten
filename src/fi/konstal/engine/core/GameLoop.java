package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.map.tiled.TiledMap;
import fi.konstal.engine.util.*;

import fi.konstal.example.game1.util.KeyInput;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by e4klehti on 14.11.2017.
 */
public interface GameLoop {
    static List<? super GameObject> gameObjects = new CopyOnWriteArrayList<>();

    void init(); //Handle level initialization
    void handle(long startTime); // JavaFX Animationtimer uses this, extend your class from it
    void renderGameObject(GameObject go);
    void checkLevelWin();
    void setMainCanvas(Canvas mainCanvas);
    void setShowHitbox(boolean showHitbox);
    Camera getCamera();
    void setCamera(Camera camera);
    List<Level> getLevels();
    void addLevel(Level level);
    void removeLevel(Level level);
    void switchLevel();
    void clear();
    void setRunning(boolean running);

    static void addGameObject(GameObject... gos) {
        for(GameObject go : gos){
            gameObjects.add(go);
        }
    }

    static void removeDeadGameActors() {
        for(int i = 0; i < gameObjects.size(); i++) {
            if(gameObjects.get(i) instanceof GameActor && !((GameActor)gameObjects.get(i)).isAlive()) {
                gameObjects.remove(gameObjects.get(i));
            }
        }
        //Old non-generic way
//        for(GameObject go: gameObjects) {
//
//            if(go instanceof GameActor && !((GameActor)go).isAlive()) {
//                gameObjects.remove(go);
//            }
//        }
    }

    static List<GameObject> getGameObjects() {
        return (List<GameObject>) gameObjects;
    }
    static void addAllGameObjects(List<? extends GameObject> goList) {
        gameObjects.addAll(goList);
    }
}


