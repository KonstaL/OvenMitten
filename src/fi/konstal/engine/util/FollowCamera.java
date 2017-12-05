package fi.konstal.engine.util;


import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.GameObject;
import javafx.scene.canvas.Canvas;

/**
 * Created by e4klehti on 19.11.2017.
 */
public class FollowCamera extends BareCamera {
    private GameObject go; //centers on this
    private Canvas mainCanvas;
    private int mapW, mapH;

    public FollowCamera(GameObject go, Canvas mainCanvas, int mapW, int mapH) {
        this.go = go;
        this.mainCanvas = mainCanvas;
        this.mapH = mapH;
        this.mapW = mapW;
        setxOffset(0);
        setyOffset(0);
    }

    @Override
    public void move(float xAmount, float yAmount) {
        center();
    }

    public void center() {
        float xOffset = (float) mainCanvas.getWidth()/2 + go.getWidth()/2;
        float yOffset = (float) mainCanvas.getHeight()/2 + go.getHeight()/2;

        //Checks that Camera doesnt go over map and show whitespace
        if((go.getX() - xOffset) > 0 && go.getX() + xOffset< mapW) {
            setxOffset(go.getX() - xOffset);
        }
        if(go.getY() - yOffset > 0 && go.getY() + yOffset < mapH) {
            setyOffset(go.getY() - yOffset);
        }
    }



    public GameObject getGameObject() {
        return go;
    }

    public void setGameObject(GameActor go) {
        this.go = go;
    }
}
