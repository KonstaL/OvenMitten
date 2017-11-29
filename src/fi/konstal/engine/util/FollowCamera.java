package fi.konstal.engine.util;


import fi.konstal.engine.GameActor;
import javafx.scene.canvas.Canvas;

/**
 * Created by e4klehti on 19.11.2017.
 */
public class FollowCamera extends BareCamera {
    private GameActor go; //center on this
    private Canvas mainCanvas;

    public FollowCamera(GameActor go, Canvas mainCanvas) {
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



    public GameActor getGameObject() {
        return go;
    }

    public void setGameObject(GameActor go) {
        this.go = go;
    }
}
