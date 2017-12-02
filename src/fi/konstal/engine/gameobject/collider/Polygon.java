package fi.konstal.engine.gameobject.collider;


import java.util.ArrayList;
import java.util.List;

public class Polygon extends javafx.scene.shape.Polygon implements Collider{
    private int x, y;
    private double[] origPoints;

    public Polygon(int x, int y, double... points) {
        this.origPoints = points;
        this.x = x;
        this.y = y;

    }
    @Override
    public void update(int x, int y) {
        setX(x);
        setY(y);
        updatePoints();
    }

    public void updatePoints() {
        //Add x and y coordinate values to all points
        List<Double> updatedPoints = new ArrayList<>();
        for(int i = 0; i < origPoints.length; i++) {
            if(i%2 == 0) {
                updatedPoints.add(origPoints[i] + x);
            } else {
                updatedPoints.add(origPoints[i] + y);
            }
        }
        getPoints().clear();
        getPoints().addAll(updatedPoints);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
