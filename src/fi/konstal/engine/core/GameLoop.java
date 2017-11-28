package fi.konstal.engine.core;

import fi.konstal.engine.GameObjects.Enemy;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.GameObject;
import fi.konstal.engine.GameObjects.MainPlayer;
import fi.konstal.engine.map.tiled.TiledMap;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;


import java.util.ArrayList;

/**
 * Created by e4klehti on 14.11.2017.
 */
public class GameLoop extends AnimationTimer {
    int counter = 0;
    private ArrayList<GameObject> gol;
    private Canvas mainCanvas;
    private int fps;
    private long fpsStart;
    private boolean showFps;
    private static Map map;
    public static Camera camera;

    public GameLoop(Canvas canvas, boolean showFps) {
        mainCanvas = canvas;
        this.showFps = showFps;
        gol = new ArrayList<>();
        camera = new Camera(mainCanvas);
    }


    @Override
    public void handle(long startTime) {
        counter++;



        //If the time has been reset, get current time
        if(fpsStart == 0 && showFps) {
            fpsStart = System.nanoTime();
        }

        //Clear the canvas
        mainCanvas.getGraphicsContext2D().clearRect(0,0,mainCanvas.getWidth(), mainCanvas.getHeight());

        if(map != null) {
            //draw background map
            map.draw(mainCanvas.getGraphicsContext2D(), camera);
        }



        //Draw the actual image
        for(GameObject go : gol) {
            go.move();
            System.out.println(go.getX());
            System.out.println(go.getY());

            //TESTING
            if (go instanceof Enemy) {
                Enemy e = (Enemy) go;

                mainCanvas.getGraphicsContext2D().drawImage(go.getSp().cycleAnimation(),
                        go.getX() - camera.getxOffset(), go.getY() - camera.getyOffset());

            } else {
                mainCanvas.getGraphicsContext2D().drawImage(go.getImage(),
                        go.getX() - camera.getxOffset(), go.getY() - camera.getyOffset());
            }
        }

        //Center viewport
        camera.center();

        //Check win
        //checkWin();



        //If it's been over a second since last fps print, print fps and clear values
        if(System.nanoTime() - fpsStart >= 1_000_000_000 && showFps) {
            System.out.println("FPS: " + fps);
            fpsStart = 0;
            fps = 0;
        }

        //Add fps per loop
        if(showFps){
            fps++;
        }
    }


    public void checkWin() {
//        //Hardcoded for testing
//        Rectangle2D winZone = tm.getObjectLayer("Win layer").getObject("winZone").getRectangle();
//        if(winZone.intersects(gol.get(1).getBounds())) {
//            System.out.println("YOURE IN THE WINZONE!!");
//        }
//        Rectangle2D killZone = tm.getObjectLayer("Win layer").getObject("killZone").getRectangle();
//        if(killZone.intersects(gol.get(1).getBounds())) {
//            System.out.println("YOURE IN THE KILLZONE!!");
//            this.stop();
//            System.exit(0);
//        }
    }

    public void addGameObject(GameObject go) {
        gol.add(go);
        if(go instanceof MainPlayer) {
            camera.setGameObject(go);
        }
    }

    public void addMap(Map map) {
        this.map = map;
    }

    public void setCamera(Camera c) {
        this.camera = c;
    }
}


