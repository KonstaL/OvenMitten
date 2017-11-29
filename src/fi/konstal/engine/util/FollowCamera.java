package fi.konstal.engine.util;


import fi.konstal.engine.Game;
import fi.konstal.engine.GameObject;
import javafx.scene.canvas.Canvas;

/**
 * Created by e4klehti on 19.11.2017.
 */
public class FollowCamera extends BareCamera {
    private GameObject go; //center on this
    private Canvas mainCanvas;

    public FollowCamera(GameObject go, Canvas mainCanvas) {
        this.go = go;
        this.mainCanvas = mainCanvas;
        setxOffset(0);
        setyOffset(0);
    }

    @Override
    public void move(float xAmount, float yAmount) {
        center();
    }

    public void center() {
        setxOffset(go.getX() - (float) mainCanvas.getWidth()/2 + go.getWidth()/2);
        setyOffset(go.getY() - (float) mainCanvas.getHeight()/2 + go.getHeight()/2);
    }



    public GameObject getGameObject() {
        return go;
    }

    public void setGameObject(GameObject go) {
        this.go = go;
    }
}
