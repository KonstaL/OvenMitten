package fi.konstal.engine.map.tiled;

import java.util.ArrayList;
import java.util.List;

/**
 * An TiledMap ObjectLayer
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class MapObjectLayer {
    private String name;
    private List<MapObject> mapObjects;

    /**
     * Instantiates a new Map object layer.
     *
     * @param name       the name mof the layer
     * @param mapObjects A list of the map objects
     */
    public MapObjectLayer(String name, List<MapObject> mapObjects) {
        this.mapObjects = new ArrayList<>();

        this.name = name;
        this.mapObjects = mapObjects;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of the map objects.
     *
     * @return the list of map objects
     */
    public List<MapObject> getMapObjects() {
        return mapObjects;
    }

    /**
     * Gets an single object by it's name.
     *
     * if object is not found, returns a null
     *
     * @param name The name to search
     * @return the object
     */
    public MapObject getObject(String name) {
        for (int i = 0; i < mapObjects.size(); i++) {
            if (mapObjects.get(i).getName().equals(name)) {
                return mapObjects.get(i);

            }
        }
        return null;
    }
}
