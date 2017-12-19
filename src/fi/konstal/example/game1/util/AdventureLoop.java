package fi.konstal.example.game1.util;

import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.core.Level;
import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.MapObject;
import fi.konstal.engine.map.tiled.TiledMap;
import fi.konstal.engine.util.*;


import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import java.util.*;

public class AdventureLoop extends AnimationTimer implements GameLoop, GameObservable, GameObserver  {

    private static List <GameObserver> observers = new ArrayList<>(); //temp testing

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

    public AdventureLoop(Canvas canvas, Level level, boolean showHitbox, boolean showFps) {
        this.mainCanvas = canvas;
        this.showHitbox = showHitbox;
        this.showFps = showFps;
        this.levels = new ArrayList<>();
        this.goList = GameLoop.getGameObjects();

        levels.add(level);
        currentLevel = level;
        loadLevel();
        init();
    }

    public void init() {
        this.camera = new FollowCamera(goList.get(0), mainCanvas, map);

        KeyboardInput input = new KeyInput((GameActor) goList.get(0));
        //input.showInputs(true);
        input.setRestrictedMovement(true);

        mainCanvas.setOnKeyPressed(input);
        mainCanvas.setOnKeyReleased(input);
        mainCanvas.setFocusTraversable(true);

        this.isRunning = true;
    }

    private void loadLevel() {
        goList.addAll(currentLevel.getGameObjects());
        map = currentLevel.getMap();
        deniedAreas = ((TiledMap)map).getObjectLayer("Collision").getMapObjects();
        currentLevel.getBgm().play();
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
        GameLoop.removeDeadGameActors();


        //draw background map
        map.draw(mainCanvas.getGraphicsContext2D(), camera);

        //Center viewport
        camera.move(0, 0);

        //show denied areas
        if(showHitbox) {
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



    public void renderGameObject(GameObject go) {
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


                if(go instanceof Hero) {
                    for(GameObject go2 : goList) {
                        if(go2 instanceof GameActor && !(go2 instanceof Hero)) {
                            if (((GameActor) go).collides(((GameActor) go2).getCollider())) {

                                ((GameActor) go).handleCollision((Zone)go2);
                                ((GameActor) go2).handleCollision((Zone)go);
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

    public void checkLevelWin() {

        if(GameLoop.getGameObjects().size() ==1) {
            notifyObservers(StateMessage.WON);
        }
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

    public void setMap(Map map) {
        this.map = map;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public List<Level> getLevels() {
        return levels;
    }

    public void addLevel(Level level) {
        levels.add(level);
    }


    public void addObserver(GameObserver o) {
        observers.add(o);
    }


    public void removeObserver(GameObserver o) {
        observers.remove(o);
    }


    public void notifyObservers(StateMessage arg) {
        for(GameObserver o : observers) {
            o.update(this, arg);
        }
    }


    public void update(GameObservable o, StateMessage arg) {
        //just pass it to the next observer
        notifyObservers(arg);
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

    @Override
    public void removeLevel(Level level) {

    }

    @Override
    public void switchLevel() {

    }

    @Override
    public void clear() {

    }

}


