package fi.konstal.engine.util;


import fi.konstal.engine.gameobject.GameObject;
import fi.konstal.engine.map.Map;
import javafx.scene.canvas.Canvas;

/**
 * Created by e4klehti on 19.11.2017.
 */
public class FollowCamera extends BareCamera {
    private GameObject go; //centers on this
    private Canvas mainCanvas;
    private int mapW, mapH;

    /**
     * Instantiates a new Follow camera.
     *
     * @param gameObject the game object which it's going to follow
     * @param mainCanvas the canvas for centering
     * @param map        the map
     */
    public FollowCamera(GameObject gameObject, Canvas mainCanvas, Map map) {
        this.go = gameObject;
        this.mainCanvas = mainCanvas;
        this.mapW = (int) map.getWidth();
        this.mapH = (int) map.getHeight();
        setXOffset(0);
        setYOffset(0);
    }

    /**
     * Just calls center()
     */
    @Override
    public void move(float xAmount, float yAmount) {
        center();
    }

    /**
     * Centers on the tracked gameObject.
     */
    public void center() {
        float xOffset = (float) mainCanvas.getWidth()/2 + go.getWidth()/2;
        float yOffset = (float) mainCanvas.getHeight()/2 + go.getHeight()/2;

        //Checks that Camera doesn't go over map and show whitespace
        if((go.getX() - xOffset) > 0 && go.getX() - go.getWidth() + xOffset< mapW) {
            setXOffset(go.getX() - xOffset);
        }
        if(go.getY() - yOffset > 0 && go.getY() - go.getHeight()+ yOffset < mapH) {
            setYOffset(go.getY() - yOffset);
        }
    }


    /**
     * Gets game tracked object.
     *
     * @return the game object
     */
    public GameObject getGameObject() {
        return go;
    }

    /**
     * Sets game tracked object.
     *
     * @param go the go
     */
    public void setGameObject(GameObject go) {
        this.go = go;
    }
}
