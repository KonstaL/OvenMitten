package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<GameObject> gameObjects;
    private Map map;

    public Level(Map map) {
        this.map = map;
        this.gameObjects = new ArrayList<>();
    }


    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
