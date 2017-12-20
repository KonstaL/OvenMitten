package fi.konstal.engine.map.tiled;

/**
 * Created by e4klehti on 19.11.2017.
 */


import java.util.ArrayList;


/**
 * The type TileLayer.
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
     * @return the tiles
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
