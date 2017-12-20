package fi.konstal.engine.map.tiled;

import java.util.ArrayList;
import java.util.List;


/**
 * The layer holding all the individual tiles
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class TileLayer {
    private String name;
    private int width;
    private int height;
    private ArrayList<Tile> tiles = new ArrayList<>();

    /**
     * Instantiates a new TileLayer.
     *
     * @param name   the name
     * @param width  the width
     * @param height the height
     * @param tiles  the tiles
     */
    public TileLayer(String name, int width, int height,
                     ArrayList<Tile> tiles) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
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
     * Gets tiles.
     *
     * @return a List of all tiles
     */
    public List<Tile> getTiles() {
        return tiles;
    }
}
