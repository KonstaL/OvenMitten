package fi.konstal.engine.util;


import fi.konstal.engine.GameObject;
import javafx.scene.canvas.Canvas;

/**
 * Created by e4klehti on 19.11.2017.
 */
public class Camera {
    private float xOffset;
    private float yOffset;
    private GameObject go; //center on this
    private Canvas mainCanvas;

    public Camera(float xOffset, float yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;

    }
    public Camera(Canvas mainCanvas) {
        this.xOffset = 0;
        this.yOffset = 0;
        this.mainCanvas = mainCanvas;
    }

    public Camera() {
        this.xOffset = 0;
        this.yOffset = 0;
    }

    public void center() {
        xOffset = go.getX() - (float) mainCanvas.getWidth()/2 + go.getWidth()/2;
        yOffset = go.getY() - (float) mainCanvas.getHeight()/2 + go.getHeight()/2;
    }

    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

    public GameObject getGameObject() {
        return go;
    }

    public void setGameObject(GameObject go) {
        this.go = go;
    }
}
