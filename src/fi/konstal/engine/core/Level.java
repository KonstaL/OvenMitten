package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;

import javafx.scene.media.MediaPlayer;
import java.util.List;

/**
 * Level works as a template for all levels
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
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
     * Sets the map
     *
     * @param map the map to set
     */
    void setMap(String Key, Map map);

    /**
     * Return a MediaPlayer containing with the levels background music
     *
     * @return A mediaplayer containing background music
     */
    MediaPlayer getBgm();

    /**
     * Sets the levels mediaplayer
     *
     * @param bgm the mediaplayer to be set
     */
    void setBgm(String key, MediaPlayer bgm);
}
