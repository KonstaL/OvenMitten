package fi.konstal.engine.gameobject;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Calendar;
import java.util.List;

public abstract class Zone extends GameObject {
    private Collider collider;

    public Zone(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.collider = new Rectangle(x, y, width, height);
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
    }


    public void renderCollider(GraphicsContext gc, Camera camera) {
        gc.setStroke(Color.RED);

        if(collider instanceof Rectangle) {
            Rectangle re = (Rectangle) collider;
            gc.strokeRect(
                    getX()- camera.getxOffset(),
                    getY() - camera.getyOffset(),
                    re.getWidth(),
                    re.getHeight()
            );
        } else if(collider instanceof Circle) {
            Circle ci = (Circle) collider;
            gc.strokeOval(
                    getX() - camera.getxOffset(),
                    getY() - camera.getyOffset(),
                    ci.getRadius(),
                    ci.getRadius()
            );

        } else if(collider instanceof Ellipse ) {
            Ellipse el = (Ellipse) collider;
            gc.strokeOval(
                    getX()- camera.getxOffset(),
                    getY() - camera.getyOffset(),
                    el.getRadiusX(),
                    el.getRadiusY()
            );

        } else if(collider instanceof Polygon) {
            List<Double> points = ((Polygon) collider).getPoints();
            double[] xCoord = new double[(points.size())/2];
            double[] yCoord = new double[(points.size())/2];

            //TODO: Reformat this away
            int yCounter = 0;
            int xCounter = 0;

            for(int i = 0; i < points.size(); i++) {
                if(i%2 == 0) {
                    xCoord[xCounter] = points.get(i);
                    xCounter++;
                } else {
                    yCoord[yCounter] = points.get(i);
                    yCounter++;
                }
            }

            gc.strokePolygon(
                    xCoord,
                    yCoord,
                    points.size()/2);
        } else {
            throw new RuntimeException("Colliders rendering is not implemented!");
        }

    }

    public boolean collides(Collider c) {
        //TODO: IMPLEMENT THIS
        return true;
    }
}
