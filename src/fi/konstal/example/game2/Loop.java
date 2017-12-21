package fi.konstal.example.game2;

import fi.konstal.engine.camera.BareCamera;
import fi.konstal.engine.camera.Camera;
import fi.konstal.engine.core.*;
import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.util.*;
import fi.konstal.example.game2.util.KeyInput;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

import java.io.Serializable;
import java.util.*;

/**
 * The type Loop.
 *
 * Uhm, I actually ran outta time javadockin, so the rest this autogenerated.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class Loop extends AnimationTimer implements GameLoop, GameObservable, GameObserver, Serializable {
    private List <GameObserver> observers;
    private List<Level> levels;
    private List<MapObject> deniedAreas;
    private Level currentLevel;
    private transient Canvas mainCanvas; //needed for decoupling
    private int fps;
    private long fpsStart;
    private boolean showFps;
    private boolean showHitbox;
    private boolean isRunning;
    private static Map map;
    private Camera camera;
    private List<GameObject> gol;

    /**
     * Instantiates a new Loop.
     *
     * @param canvas     the canvas
     * @param level      the level
     * @param showHitbox the show hitbox
     * @param showFps    the show fps
     */
    public Loop(Canvas canvas, Level level, boolean showHitbox, boolean showFps) {
        this.observers = new ArrayList<>();
        this.mainCanvas = canvas;
        this.showHitbox = showHitbox;
        this.showFps = showFps;
        this.levels = new ArrayList<>();
        this.deniedAreas = new ArrayList<>();
        this.gol = GameLoop.getGameObjects();

        levels.add(level);
        currentLevel = level;
        loadLevel();
        init();
    }

    public void init() {
        this.camera = new BareCamera();

        KeyboardInput input = new KeyInput((GameActor)gol.get(0));
        //input.setRestrictedMovement(false);
        //input.showInputs(true);

        mainCanvas.setOnKeyPressed(input);
        mainCanvas.setOnKeyReleased(input);
        mainCanvas.setFocusTraversable(true);

        ((SpaceShip)gol.get(0)).addObserver(this);
        this.isRunning = true;
    }


    private void loadLevel() {
        gol.addAll(currentLevel.getGameObjects());
        map = currentLevel.getMap();
        currentLevel.getBgm().play();
    }

    @Override
    public void handle(long startTime) {
        if(isRunning) {
            //If the time has been reset, get current time
            if (fpsStart == 0 && showFps) {
                fpsStart = System.nanoTime();
            }

            //Clear the canvas
            mainCanvas.getGraphicsContext2D().clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());

            //Remove dead Game_actors
            GameLoop.removeDeadGameActors();



            //draw background map
            map.draw(mainCanvas.getGraphicsContext2D(), camera);

            //Center viewport
            camera.move(0, 0);


            //Draw and update GameObjects
            for (GameObject go : gol) {
                renderGameObject(go);
            }

            //Check win
            checkLevelWin();


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
    }



    public void renderGameObject(GameObject go) {
        //Updates every GameObject
        go.update();

        if(go instanceof Decoration) {
            mainCanvas.getGraphicsContext2D().drawImage(
                    ((Decoration) go).getSprite(),
                    go.getX() - camera.getXOffset(),
                    go.getY() - camera.getYOffset(),
                    go.getWidth(),
                    go.getHeight());

        } else if (go instanceof Zone) {

            if (go instanceof GameActor) {
                ((GameActor) go).move(map, deniedAreas);
                mainCanvas.getGraphicsContext2D().drawImage(
                        ((GameActor) go).getSprite().getImage(),
                        go.getX() - camera.getXOffset(),
                        go.getY() - camera.getYOffset(),
                        go.getWidth(),
                        go.getHeight());

                //Testing
                if(go instanceof Projectile) {
                    checkCollision(go);

                }
            }

            if (showHitbox) {
                ((Zone) go).renderCollider(mainCanvas.getGraphicsContext2D(), camera);
            }
        }
    }

    private void checkCollision(GameObject go) {
        for(GameObject go2 : gol) {

            //If its collideable and isn't the projectile itself
            if(go2 instanceof GameActor && !go2.equals(go)) {

                //if it has a parent
                if(((Projectile) go).getParent().isPresent()) {

                    //If the projectile collides with something else than it's parent
                    if (((Projectile) go).collides(((GameActor) go2).getCollider()) &&
                            ((Projectile) go).getParent().get() != go2.getClass()) {

                        //If go2 is a projectile and is from the same parent class
                        if (!(go2 instanceof Projectile) ||
                                ((Projectile) go).getParent().get() != ((Projectile)go2).getParent().get()) {

                            //Collide
                            ((Projectile) go).handleCollision((Zone)go2);
                            ((GameActor) go2).handleCollision((Zone)go);
                        } else {
                            //Do nothing
                        }
                    }
                } else {
                    //If it collides with any object
                    if (((Projectile) go).collides(((GameActor) go2).getCollider())) {
                        ((Projectile) go).handleCollision((Zone)go2);
                        ((GameActor) go2).handleCollision((Zone)go);
                    }
                }
            }
        }
    }

    public void checkLevelWin() {
        if(gol.size() ==1) {
            switchLevel();
        }
    }

    /**
     * Gets main canvas.
     *
     * @return the main canvas
     */
    public Canvas getMainCanvas() {
        return mainCanvas;
    }

    /**
     * Sets main canvas.
     *
     * @param mainCanvas the main canvas
     */
    public void setMainCanvas(Canvas mainCanvas) {
        this.mainCanvas = mainCanvas;
    }

    /**
     * Is show hitbox boolean.
     *
     * @return the boolean
     */
    public boolean isShowHitbox() {
        return showHitbox;
    }

    public void setShowHitbox(boolean showHitbox) {
        this.showHitbox = showHitbox;
    }

    /**
     * Gets map.
     *
     * @return the map
     */
    public static Map getMap() {
        return map;
    }

    /**
     * Sets map.
     *
     * @param map the map
     */
    public void setMap(Map map) {
        this.map = map;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public List<Level> getLevels() {
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
        if(arg == StateMessage.PAUSE) {
            isRunning = false;
            currentLevel.setGameObjects(GameLoop.getGameObjects()); //Clones the state
        }
        //Pass the update
        notifyObservers(arg);
    }

    @Override
    public void removeLevel(Level level) {
        levels.remove(level);
    }

    @Override
    public void switchLevel() {
        int levelIndex = levels.indexOf(currentLevel);
        if (levels.size() > levelIndex+1) {
            currentLevel.setGameObjects(GameLoop.getGameObjects()); //Clones the state
            currentLevel.getBgm().stop(); //Stop music

            currentLevel = levels.get((levelIndex + 1)); //Switch level
            loadLevel();
            notifyObservers(StateMessage.LEVEL_CLEARED);
        } else {
            notifyObservers(StateMessage.WON);
        }
    }

    @Override
    public void clear() {
        currentLevel.getBgm().stop();
        gol.clear();
    }
}