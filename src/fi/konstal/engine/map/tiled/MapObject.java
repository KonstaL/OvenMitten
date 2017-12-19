package fi.konstal.engine.map.tiled;

import fi.konstal.engine.gameobject.collider.Collider;
import fi.konstal.engine.gameobject.collider.Rectangle;


/**
 * Created by e4klehti on 21.11.2017.
 */
public class MapObject {
    private String name;
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;
    private Collider collider;




    public MapObject(int id, String name, int x, int y,
                     int width, int height) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.collider = new Rectangle(x, y, width, height);
    }


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public Collider getCollider() {
        return collider;
    }
}

