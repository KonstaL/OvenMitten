package fi.konstal.engine.map.tiled;


/**
 * A single Tile in the TileLayer.
 */
public class Tile {
    private int imageCoordinate;
    private int x;
    private int y;

    /**
     * Instantiates a new Tile.
     *
     * @param imageCoordinate the image coordinate
     * @param x               the x-coordinate
     * @param y               the y-coordinate
     */
    public Tile(int imageCoordinate, int x, int y) {
        this.imageCoordinate = imageCoordinate;
        this.x = x;
        this.y = y;
    }


    /**
     * Gets image coordinate.
     *
     * @return the image coordinate
     */
    public int getImageCoordinate() {
        return imageCoordinate;
    }


    /**
     * Gets x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }


    /**
     * Gets y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }
}
