package fi.konstal.engine;

import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.KeyboardInput;
import fi.konstal.engine.util.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by konka on 15.11.2017.
 */
public abstract class GameActor {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private int xVelocity;
    private int yVelocity;
    private KeyboardInput keyboardInputListener;
    private Enum<Direction> direction;
    private Collider collider;
    public Animation sp;

    public GameActor(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;;
    }

    public GameActor(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = new Image("OvenMitten.jpeg");
    }


    abstract public void move();
    abstract public void getInput();
    abstract public void update(Canvas c);

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public KeyboardInput getInputlistener() {
        return keyboardInputListener;
    }

    public void setInputlistener(KeyboardInput keyboardInputListener) {
        this.keyboardInputListener = keyboardInputListener;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }


    public Animation getSp() {
        return sp;
    }

    public void setSp(Animation sp) {
        this.sp = sp;
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
}

enum Direction {
    UP, DOWN, LEFT, RIGHT;
}
