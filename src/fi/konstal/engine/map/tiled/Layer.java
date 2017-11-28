package fi.konstal.engine.map.tiled;

/**
 * Created by e4klehti on 19.11.2017.
 */


import java.util.ArrayList;


public class Layer {
    private String name;
    private int width;
    private int height;
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Layer(String name, int width, int height,
                 ArrayList<Tile> tiles) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
