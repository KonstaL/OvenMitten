package fi.konstal.engine.tiled;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by e4klehti on 21.11.2017.
 */
public class MapObjectLayer {
    private String name;
    private List<MapObject> mapObjects;

    public MapObjectLayer(String name, List<MapObject> mapObjects) {
        this.mapObjects = new ArrayList<>();

        this.name = name;
        this.mapObjects = mapObjects;
    }

    public String getName() {
        return name;
    }

    public List<MapObject> getMapObjects() {
        return mapObjects;
    }

    //Search object by name
    public MapObject getObject(String name) {
        for (int i = 0; i < mapObjects.size(); i++) {
            if (mapObjects.get(i).getName().equals(name)) {
                return mapObjects.get(i);

            }
        }
        return null;
    }
}
