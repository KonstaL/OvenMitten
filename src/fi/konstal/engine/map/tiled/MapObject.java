package fi.konstal.engine.map.tiled;

import fi.konstal.engine.gameobject.collider.Collider;
import fi.konstal.engine.gameobject.collider.Rectangle;


/**
 * An object extracted from a tiledmaps object layer
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class MapObject {
    private String name;
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;
    private Collider collider;


    /**
     * Instantiates a new Map object.
     *
     * @param id     the id on tiledmap
     * @param name   the name on tiledmap
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param width  the width on tiledmap
     * @param height the height on tiledmap
     */
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


    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
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
     * Gets the x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * Gets y-coordinate.
     *
     * @return the y-coordinate.
     */
    public int getY() {
        return y;
    }


    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }


    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets collider.
     *
     * @return the collider
     */
    public Collider getCollider() {
        return collider;
    }
}

