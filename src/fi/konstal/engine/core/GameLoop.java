package fi.konstal.engine.core;

import fi.konstal.engine.GameObjects.Enemy;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.GameActor;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


import java.util.ArrayList;

/**
 * Created by e4klehti on 14.11.2017.
 */
public class GameLoop extends AnimationTimer {
    int counter = 0;
    private ArrayList<GameActor> gol;
    private Canvas mainCanvas;
    private int fps;
    private long fpsStart;
    private boolean showFps;
    private boolean showHitbox;
    private static Map map;
    private Camera camera;

    public GameLoop(Canvas canvas, Map map, Camera camera, boolean showHitbox, boolean showFps) {
        this.mainCanvas = canvas;
        this.map = map;
        this.showHitbox = showHitbox;
        this.showFps = showFps;
        this.gol = new ArrayList<>();
        this.camera = camera;
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
        for(GameActor go : gol) {
            go.update(mainCanvas);
            go.move();


            //TESTING
            if (go instanceof Enemy) {
                Enemy e = (Enemy) go;

                mainCanvas.getGraphicsContext2D().drawImage(go.getSp().cycleAnimation(),
                        go.getX() - camera.getxOffset(), go.getY() - camera.getyOffset());

            } else {
                mainCanvas.getGraphicsContext2D().drawImage(go.getImage(),
                        go.getX() - camera.getxOffset(), go.getY() - camera.getyOffset(), go.getWidth(), go.getHeight());


                if(showHitbox)  {
                    mainCanvas.getGraphicsContext2D().setStroke(Color.RED);
                    mainCanvas.getGraphicsContext2D().strokeRect(go.getX()- camera.getxOffset(),
                            go.getY() - camera.getyOffset(), go.getWidth(), go.getHeight());
                }
            }
        }

        //Center viewport
        camera.move(0,0);

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

    public void addGameObject(GameActor go) {
        gol.add(go);
    }

    public void addMap(Map map) {
        this.map = map;
    }
}


