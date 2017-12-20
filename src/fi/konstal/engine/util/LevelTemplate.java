package fi.konstal.engine.util;

import fi.konstal.engine.core.Level;
import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;

import javafx.scene.media.MediaPlayer;
import java.io.Serializable;
import java.util.List;

public abstract class LevelTemplate implements Level, Serializable {
    private List<? super GameObject> gameObjects;
    private transient Map map;
    private transient MediaPlayer bgm;


    @Override
    public List<GameObject> getGameObjects() {
        return (List<GameObject>) gameObjects;
    }

    @Override
    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
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


