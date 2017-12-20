package fi.konstal.engine.gameobject;


/**
 * The base GameObject from which all active GameObjects extend from
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public abstract class GameObject {
    private int x;
    private int y;
    private int width;
    private int height;

    /**
     * Instantiates a new Game object.
     *
     * @param x      the x -coordinate
     * @param y      the y -coordinate
     * @param width  the width
     * @param height the height
     */
    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Planned to be used in initialazing the Object, but for now, does nothing.
     */
    public void init() { }

    /**
     * Update every cycle.
     */
    public abstract void update();

    /**
     * Gets x-coordinate.
     *
     * @return the x-coordinate
     */
    public int getX() { return x; }

    /**
     * Sets x-coordinate.
     *
     * @param x the x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y-coordinate.
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y-coordinate.
     *
     * @param y the y-coordinate
     */
    public void setY(int y) {
        this.y = y;
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
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
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
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
