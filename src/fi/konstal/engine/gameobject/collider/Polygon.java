package fi.konstal.engine.gameobject.collider;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A Polygon shaped Collider.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Polygon extends javafx.scene.shape.Polygon implements Collider, Serializable{
    private int x, y;
    private double[] origPoints;

    /**
     * Instantiates a new Polygon.
     *
     * @param x      the x-coordinate
     * @param y      the y-coordinate
     * @param points the points that make up the polygon
     */
    public Polygon(int x, int y, double... points) {
        this.origPoints = points;
        this.x = x;
        this.y = y;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(int x, int y) {
        setX(x);
        setY(y);
        updatePoints();
    }

    /**
     * Update all the points to match the new x- and y-coordinates
     */
    public void updatePoints() {
        //Add x and y coordinate values to all points
        List<Double> updatedPoints = new ArrayList<>();
        for(int i = 0; i < origPoints.length; i++) {
            //X Point
            if(i%2 == 0) {
                updatedPoints.add(origPoints[i] + x);
            //Y point
            } else {
                updatedPoints.add(origPoints[i] + y);
            }
        }
        getPoints().clear();
        getPoints().addAll(updatedPoints);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Sets a new base x-coordinate
     *
     * @param x the x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Sets a new base y-coordinate
     *
     * @param y the y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
