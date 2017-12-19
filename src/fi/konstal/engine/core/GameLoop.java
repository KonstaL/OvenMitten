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
    static List<GameObject> gameObjects = new CopyOnWriteArrayList<>();

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



    //public List<GameObject> getGol();
    //public void setGol(ArrayList<GameObject> gol);
    //public Canvas getMainCanvas();




    static void addGameObject(GameObject... gos) {
        for(GameObject go : gos){
            gameObjects.add(go);
        }
    }



    static void removeDeadGameActors() {
//        //for normal List
//        for(Iterator it = gol.iterator(); it.hasNext();) {
//            GameActor pr = (GameActor) it.next();
//
//            if (!pr.isAlive()) {
//                it.remove();
//            }
//        }

        //For CopyOnWriteArraylist
        for(GameObject go: gameObjects) {

            if(go instanceof GameActor && !((GameActor)go).isAlive()) {
                gameObjects.remove(go);
            }
        }
    }

    static List<GameObject> getGameObjects() {
        return gameObjects;
    }
    static void addAllGameObjects(List<GameObject> goList) {
        gameObjects.addAll(goList);
    }
}


