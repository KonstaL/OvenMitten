package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;

import java.util.ArrayList;
import java.util.List;

public interface Level {

    List<GameObject> getGameObjects();
    void setGameObjects(List<GameObject> gameObjects);
    Map getMap();
    void setMap(Map map);
}
