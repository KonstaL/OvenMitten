package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;

import javafx.scene.media.MediaPlayer;
import java.util.List;

/**
 * Level works as a template for all levels
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public interface Level {

    /**
     * Return a list of all GameObjects contained in the level
     *
     * @return a list of all the GameObjects
     */
    List<GameObject> getGameObjects();

    /**
     * Sets the GameObjects
     *
     * @param gameObjects The GameObjects to set
     */
    void setGameObjects(List<GameObject> gameObjects);

    /**
     * Prepares the level for use
     */
    void init();

    /**
     * Returns the Levels map
     *
     * @return levels map
     */
    Map getMap();


    /**
     * Sets the levels map by an asset reference
     *
     * @param Key the reference key
     * @param map the map
     */
    void setMap(int Key, Map map);

    /**
     * Return a MediaPlayer containing with the levels background music
     *
     * @return A mediaplayer containing background music
     */
    MediaPlayer getBgm();


    /**
     * Saves an asset reference to the levels background music
     *
     * @param key the asset reference key
     * @param bgm the background music
     */
    void setBgm(int key, MediaPlayer bgm);
}
