package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;
import javafx.scene.media.MediaPlayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface Level {
    List<GameObject> getGameObjects();
    void setGameObjects(List<GameObject> gameObjects);
    void init();
    void loadAssets();
    Map getMap();
    void setMap(Map map);
    MediaPlayer getBgm();
    void setBgm(MediaPlayer bgm);


}
