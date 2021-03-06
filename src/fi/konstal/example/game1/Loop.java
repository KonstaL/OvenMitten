package fi.konstal.example.game1;

import fi.konstal.engine.camera.Camera;
import fi.konstal.engine.camera.FollowCamera;
import fi.konstal.engine.core.*;

import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.*;
import fi.konstal.engine.util.*;


import fi.konstal.example.game1.util.KeyInput;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

import java.util.*;

/**
 * The main gameloop of the game. Customized for the purposes of game1
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public class Loop extends AnimationTimer implements GameLoop, GameObservable, GameObserver {
    private static List<GameObserver> observers;
    private List<Level> levels;
    private Level currentLevel;
    private Canvas mainCanvas;
    private List<MapObject> deniedAreas;
    private int fps;
    private long fpsStart;
    private boolean showFps;
    private boolean showHitbox;
    private boolean isRunning;
    private static Map map;
    private Camera camera;
    private List<GameObject> goList;

    /**
     * Instantiates a new Loop.
     *
     * @param canvas     the canvas
     * @param level      the starting level
     * @param showHitbox Whether to show hitboxes or not
     * @param showFps    whether to show the fps
     */
    public Loop(Canvas canvas, Level level, boolean showHitbox, boolean showFps) {
        this.mainCanvas = canvas;
        this.showHitbox = showHitbox;
        this.showFps = showFps;
        this.levels = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.goList = GameLoop.getGameObjects();

        levels.add(level);
        currentLevel = level;
        loadLevel();
        init();
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
        this.camera = new FollowCamera(goList.get(0), mainCanvas, map);

        KeyboardInput input = new KeyInput((GameActor) goList.get(0));
        //input.showInputs(true);
        input.setRestrictedMovement(true);

        mainCanvas.setOnKeyPressed(input);
        mainCanvas.setOnKeyReleased(input);
        mainCanvas.setFocusTraversable(true);

        ((Hero) gameObjects.get(0)).addObserver(this);
        this.isRunning = true;
    }

    /**
     * {@inheritDoc}
     */
    private void loadLevel() {
        goList.addAll(currentLevel.getGameObjects());
        map = currentLevel.getMap();
        deniedAreas = ((TiledMap) map).getObjectLayer("Collision").getMapObjects();
        currentLevel.getBgm().play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(long startTime) {
        if (isRunning) {

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

            //show denied areas
            if (showHitbox) {
                for (MapObject mo : deniedAreas) {
                    mainCanvas.getGraphicsContext2D().strokeRect(
                            mo.getX() - camera.getXOffset(),
                            mo.getY() - camera.getYOffset(),
                            mo.getWidth(),
                            mo.getHeight()
                    );
                }
            }

            //Draw and update GameObjects
            for (GameObject go : goList) {
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


    /**
     * {@inheritDoc}
     */
    public void renderGameObject(GameObject go) {
        go.update();

        if (go instanceof Decoration) {
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


                if (go instanceof Hero) {
                    for (GameObject go2 : goList) {
                        if (go2 instanceof GameActor && !(go2 instanceof Hero)) {
                            if (((GameActor) go).collides(((GameActor) go2).getCollider())) {

                                ((GameActor) go).handleCollision((Zone) go2);
                                ((GameActor) go2).handleCollision((Zone) go);
                            }
                        }
                    }
                }
                if(go instanceof Trump) {
                    for(GameObject go2 : goList) {
                        if(go2 instanceof Projectile && ((Trump) go).collides(((GameActor) go2).getCollider())) {
                            ((GameActor) go).handleCollision((Zone) go2);
                            ((GameActor) go2).handleCollision((Zone) go);
                        }
                    }
                }
            }

            if (showHitbox) {
                ((Zone) go).renderCollider(mainCanvas.getGraphicsContext2D(), camera);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void checkLevelWin() {
        boolean levelWon = true;
        for(GameObject go : goList) {
            if(go instanceof Trump) {
                levelWon = false;
            }
        }

        if (levelWon) {
            switchLevel();
        }
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

    /**
     * {@inheritDoc}
     */
    public Camera getCamera() {
        return camera;
    }

    /**
     * {@inheritDoc}
     */
    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    /**
     * {@inheritDoc}
     */
    public List<Level> getLevels() {
        return levels;
    }

    /**
     * {@inheritDoc}
     */
    public void addLevel(Level level) {
        levels.add(level);
    }

    /**
     * {@inheritDoc}
     */
    public void addObserver(GameObserver o) {
        observers.add(o);
    }

    /**
     * {@inheritDoc}
     */
    public void removeObserver(GameObserver o) {
        observers.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    public void setRunning(boolean running) {
        this.isRunning = running;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(StateMessage arg) {
        for (GameObserver o : observers) {
            o.update(this, arg);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GameObservable o, StateMessage arg) {
        //Pass the update
        notifyObservers(arg);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void switchLevel() {
        int levelIndex = levels.indexOf(currentLevel);
        if (levels.size() > levelIndex + 1) {
            currentLevel.setGameObjects(GameLoop.getGameObjects()); //Clones the state (for saving)
            currentLevel.getBgm().stop(); //Stop music

            currentLevel = levels.get((levelIndex + 1)); //Switch level
            loadLevel();
            notifyObservers(StateMessage.LEVEL_CLEARED);
        } else {
            notifyObservers(StateMessage.WON);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeLevel(Level level) {
        //not used
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        currentLevel.getBgm().stop();
        gameObjects.clear();
    }
}


