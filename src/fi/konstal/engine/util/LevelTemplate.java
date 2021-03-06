package fi.konstal.engine.util;

import fi.konstal.engine.assetmanager.AssetManager;
import fi.konstal.engine.core.Level;
import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;

import javafx.scene.media.MediaPlayer;
import java.io.Serializable;
import java.util.List;

/**
 * A level template for making basic maps with GameObjects, a map and background music.
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public abstract class LevelTemplate implements Level, Serializable {
    private List<? super GameObject> gameObjects;
    private int mapKey;
    private int bgmKey;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getGameObjects() {
        return (List<GameObject>) gameObjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map getMap() {
        return AssetManager.getAsset(mapKey, Map.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMap(int key, Map map) {
        this.mapKey = key;
        AssetManager.addAsset(key, map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MediaPlayer getBgm() {
        return AssetManager.getAsset(bgmKey, MediaPlayer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBgm(int key, MediaPlayer bgm) {
        this.bgmKey = key;
        AssetManager.addAsset(key, bgm);
    }
}


