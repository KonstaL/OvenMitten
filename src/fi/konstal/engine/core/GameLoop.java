package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import javafx.animation.AnimationTimer;
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
        if (fpsStart == 0 && showFps) {
            fpsStart = System.nanoTime();
        }

        //Clear the canvas
        mainCanvas.getGraphicsContext2D().clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());


        //draw background map
        map.draw(mainCanvas.getGraphicsContext2D(), camera);



        //Draw the actual image
        for (GameObject go : gol) {
            if(go instanceof Decoration) {

                mainCanvas.getGraphicsContext2D().drawImage(((Decoration) go).getSprite(),
                        go.getX() - camera.getxOffset(), go.getY() - camera.getyOffset(), go.getWidth(), go.getHeight());

            } else if (go instanceof Zone) {

                if (showHitbox) {
                    ((Zone) go).renderCollider(mainCanvas.getGraphicsContext2D(), camera);
                }

                if (go instanceof GameActor) {
                    ((GameActor) go).move(map);

                    mainCanvas.getGraphicsContext2D().drawImage(((GameActor) go).getSprite().getImage(),
                            go.getX() - camera.getxOffset(), go.getY() - camera.getyOffset(), go.getWidth(), go.getHeight());
                    if (go instanceof MainPlayer) {
                        for(GameObject go2 : gol) {
                            if(!(go2 instanceof MainPlayer) && !(go2 instanceof Decoration)) {
                                if(((MainPlayer) go).collides(((GameActor)go2).getCollider())) {
                                    ((MainPlayer) go).onCollision();
                                }
                            }
                        }
                    }
                }
            }
        }


            //Center viewport
            camera.move(0, 0);

            //Check win
            //checkWin();


            //If it's been over a second since last fps print, print fps and clear values
            if (System.nanoTime() - fpsStart >= 1_000_000_000 && showFps) {
                System.out.println("FPS: " + fps);
                fpsStart = 0;
                fps = 0;
            }

            //Add fps per loop
            if (showFps) {
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
    }


    public ArrayList<GameObject> getGol() {
        return gol;
    }

    public void setGol(ArrayList<GameObject> gol) {
        this.gol = gol;
    }

    public Canvas getMainCanvas() {
        return mainCanvas;
    }

    public void setMainCanvas(Canvas mainCanvas) {
        this.mainCanvas = mainCanvas;
    }

    public boolean isShowHitbox() {
        return showHitbox;
    }

    public void setShowHitbox(boolean showHitbox) {
        this.showHitbox = showHitbox;
    }

    public static Map getMap() {
        return map;
    }

    public static void setMap(Map map) {
        GameLoop.map = map;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}


