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

    public FollowCamera(GameObject gameObject, Canvas mainCanvas, Map map) {
        this.go = gameObject;
        this.mainCanvas = mainCanvas;
        this.mapW = (int) map.getWidth();
        this.mapH = (int) map.getHeight();
        setXOffset(0);
        setYOffset(0);
    }

    @Override
    public void move(float xAmount, float yAmount) {
        center();
    }

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



    public GameObject getGameObject() {
        return go;
    }

    public void setGameObject(GameObject go) {
        this.go = go;
    }
}
