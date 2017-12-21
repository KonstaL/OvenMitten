package fi.konstal.engine.core;

import fi.konstal.engine.camera.Camera;
import fi.konstal.engine.gameobject.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Holds all the necessary methods to implement a working gameloop
 * All Classes that implement this should also implement AnimationTimer from JavaFX
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public interface GameLoop {
    /**
     * A static list which contains all the GameObjects currently in play.
     * As it is static, it can be accessed anywhere
     * <p>
     * CopyOnWriteArray is used to enable simultanious multi-threaded use
     */
    List<? super GameObject> gameObjects = new CopyOnWriteArrayList<>();

    /**
     * Initializes GameLoop
     */
    void init(); //Handle level initialization

    /**
     * This is the actual gameloop provided by AnimationTimer
     * Refreshes max. 60 times per second
     *
     *
     * @param startTime the start time
     */
    void handle(long startTime); // JavaFX Animationtimer uses this, extend your class from it

    /**
     * Renders the GameObjects
     *
     * @param go a single gameObject
     */
    void renderGameObject(GameObject go);

    /**
     * Checks if the current level is beaten
     */
    void checkLevelWin();


    /**
     * Toggles whether the Colliders of GameObjects are rendered
     *
     * @param showHitbox determines the rendering
     */
    void setShowHitbox(boolean showHitbox);

    /**
     * Gets camera.
     *
     * @return the camera
     */
    Camera getCamera();

    /**
     * Sets camera.
     *
     * @param camera the camera
     */
    void setCamera(Camera camera);

    /**
     * Gets all levels.
     *
     * @return the a list of Levels
     */
    List<Level> getLevels();

    /**
     * Adds a level.
     *
     * @param level the level to be added
     */
    void addLevel(Level level);

    /**
     * Removes a level.
     *
     * @param level the level to remove
     */
    void removeLevel(Level level);

    /**
     * Switches level.
     *
     * Called after checklevelWin
     */
    void switchLevel();

    /**
     * Clears the GameLoop of all data
     */
    void clear();

    /**
     * Sets running.
     *
     * @param running the running
     */
    void setRunning(boolean running);

    /**
     * A static method for adding GameObjects to the current Game from anywhere.
     *
     * @param gos any amount of GameObjects
     */
    static void addGameObject(GameObject... gos) {
        for(GameObject go : gos){
            gameObjects.add(go);
        }
    }

    /**
     * Remove GameActors that are set to be not alive
     */
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

    /**
     * Gets a List of GameObjects
     *
     * @return A List of current GameObjects
     */
    static List<GameObject> getGameObjects() {
        return (List<GameObject>) gameObjects;
    }

    /**
     * Add a List of Objects that extend from GameObject.
     *
     * @param goList The list that is going to be added
     */
    static void addAllGameObjects(List<? extends GameObject> goList) {
        gameObjects.addAll(goList);
    }
}


