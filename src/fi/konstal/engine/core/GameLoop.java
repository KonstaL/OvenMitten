package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.Projectile;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by e4klehti on 14.11.2017.
 */
public class GameLoop extends AnimationTimer {
    private static List<GameObject> gol;
    private static List <Projectile> projectiles;
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
        this.camera = camera;
        this.gol = new CopyOnWriteArrayList();
        this.projectiles = new CopyOnWriteArrayList();
    }


    @Override
    public void handle(long startTime) {
        //If the time has been reset, get current time
        if (fpsStart == 0 && showFps) {
            fpsStart = System.nanoTime();
        }

        //Clear the canvas
        mainCanvas.getGraphicsContext2D().clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

        //Remove dead Game_actors
        removeDeadGameActors();

        //Remove any dead projectiles
        //removeDeadProjectiles();



        //draw background map
        map.draw(mainCanvas.getGraphicsContext2D(), camera);

        //Center viewport
        camera.move(0, 0);



        //Draw the actual image

            for (GameObject go : gol) {
                renderGameObject(go);
            }






//        //Update projectiles
//        for (Projectile pr : projectiles) {
//            pr.update();
//            renderGameObject(pr);
//
//            for (GameObject go : gol) {
//                if(pr.collides(((GameActor)go).getCollider())) {
//                    ((GameActor) go).handleCollision();
//                    pr.handleCollision();
//                }
//            }
//        }
        //TODO: Clean this code

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



    public void renderGameObject(GameObject go) {
        go.update();

        if(go instanceof Decoration) {
            mainCanvas.getGraphicsContext2D().drawImage(
                    ((Decoration) go).getSprite(),
                    go.getX() - camera.getxOffset(),
                    go.getY() - camera.getyOffset(),
                    go.getWidth(),
                    go.getHeight());

        } else if (go instanceof Zone) {

            if (go instanceof GameActor) {
                ((GameActor) go).move(map);
                mainCanvas.getGraphicsContext2D().drawImage(
                        ((GameActor) go).getSprite().getImage(),
                        go.getX() - camera.getxOffset(),
                        go.getY() - camera.getyOffset(),
                        go.getWidth(),
                        go.getHeight());

//                if (go instanceof MainPlayer) {
//                    //((MainPlayer)go).renderCollider(mainCanvas.getGraphicsContext2D(), camera);
//                    for(GameObject go2 : gol) {
//                        if(!(go2 instanceof MainPlayer) && !(go2 instanceof Decoration)) {
//                            if(((MainPlayer) go).collides(((GameActor)go2).getCollider())) {
//                                ((MainPlayer) go).handleCollision();
//                            }
//                        }
//                    }
//                }
                //Testing
                if(go instanceof Projectile) {

                    for(GameObject go2 : gol) {

                        if(go2 instanceof GameActor && !(go2 instanceof Projectile)) {
                            if(((Projectile) go).getParent().isPresent()) {
                                if (((Projectile) go).collides(((GameActor) go2).getCollider()) &&
                                        !(((Projectile) go).getParent().get() == go2.getClass())) {
                                    ((Projectile) go).handleCollision();
                                    ((GameActor) go2).handleCollision();
                                }
                            } else {
                                if (((Projectile) go).collides(((GameActor) go2).getCollider())) {

                                    ((Projectile) go).handleCollision();
                                    ((GameActor) go2).handleCollision();
                                }
                            }

                        }
                    }

                }

            }

            if (showHitbox) {
                ((Zone) go).renderCollider(mainCanvas.getGraphicsContext2D(), camera);
            }
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

    public static void addGameObject(GameObject go) {
        gol.add(go);
    }
    public static void addProjectile(Projectile pr) {
        projectiles.add(pr);
    }
    public static void removeDeadProjectiles() {
        for(Iterator it = projectiles.iterator(); it.hasNext();) {
            Projectile pr = (Projectile) it.next();

            if (!pr.isAlive()) {
                it.remove();
            }
        }
    }

    public static void removeDeadGameActors() {
//        //for normal List
//        for(Iterator it = gol.iterator(); it.hasNext();) {
//            GameActor pr = (GameActor) it.next();
//
//            if (!pr.isAlive()) {
//                it.remove();
//            }
//        }

        //For CopyOnWriteArraylist
        for(GameObject go: gol) {
            if(!((GameActor)go).isAlive()) {
                gol.remove(go);
            }
        }


    }


    public List<GameObject> getGol() {
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


