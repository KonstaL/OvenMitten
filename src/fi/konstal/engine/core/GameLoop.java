package fi.konstal.engine.core;

import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.GameObservable;
import fi.konstal.engine.util.GameObserver;
import fi.konstal.engine.util.Projectile;
import fi.konstal.engine.util.StateMessage;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by e4klehti on 14.11.2017.
 */
public class GameLoop extends AnimationTimer implements GameObservable, GameObserver{
    private static List<GameObject> gol;
    private static List <GameObserver> observers = new ArrayList<>(); //temp testing
    private List<Level> levels;         //Not implemented
    private Level currentLevel;         //Not implemented
    private Canvas mainCanvas;
    private int fps;
    private long fpsStart;
    private boolean showFps;
    private boolean showHitbox;
    private boolean isRunning;
    private static Map map;
    private Camera camera;

    public GameLoop(Canvas canvas, Map map, Camera camera, boolean showHitbox, boolean showFps) {
        this.mainCanvas = canvas;
        this.map = map;
        this.showHitbox = showHitbox;
        this.showFps = showFps;
        this.camera = camera;

        this.gol = new CopyOnWriteArrayList();
        this.levels = new ArrayList<>();
        this.isRunning = true;
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


        //draw background map
        map.draw(mainCanvas.getGraphicsContext2D(), camera);

        //Center viewport
        camera.move(0, 0);



        //Draw and update GameObjects
        for (GameObject go : gol) {
            renderGameObject(go);
        }


        //TODO: Clean this code

        //Check win
        checkWin();


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

                //Testing
                if(go instanceof Projectile) {

                    for(GameObject go2 : gol) {

                        if(go2 instanceof GameActor && !(go2 instanceof Projectile)) {
                            if(((Projectile) go).getParent().isPresent()) {
                                if (((Projectile) go).collides(((GameActor) go2).getCollider()) &&
                                        !(((Projectile) go).getParent().get() == go2.getClass())) {
                                    ((Projectile) go).handleCollision((Zone)go2);
                                    ((GameActor) go2).handleCollision((Zone)go);
                                }
                            } else {
                                if (((Projectile) go).collides(((GameActor) go2).getCollider())) {

                                    ((Projectile) go).handleCollision((Zone)go2);
                                    ((GameActor) go2).handleCollision((Zone)go);
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
        //TempoKrary
        if(gol.size() ==1) {
            notifyObservers(StateMessage.WON);
        }
    }

    public static void addGameObject(GameObject go) {
        gol.add(go);
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
            if(go instanceof GameActor && !((GameActor)go).isAlive()) {
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

    public LinkedList<Level> getLevels() {
        return levels;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }

    @Override
    public void addObserver(GameObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(GameObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(StateMessage arg) {
        for(GameObserver o : observers) {
            o.update(this, arg);
        }
    }

    @Override
    public void update(GameObservable o, StateMessage arg) {
        notifyObservers(arg);
//        switch (arg) {
//            case LEVEL_CLEARED:
//                nextLevel()
//        }

    }

    public void nextLevel() {
        int levelIndex = levels.indexOf(currentLevel);
        Level nextLevel = levels.get((levelIndex + 1));
        if (nextLevel != null) {
            currentLevel = nextLevel;
            notifyObservers(StateMessage.PLAYING);
        } else {
            notifyObservers(StateMessage.WON);
        }
    }
}


