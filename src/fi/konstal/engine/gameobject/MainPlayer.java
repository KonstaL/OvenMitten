package fi.konstal.engine.gameobject;


import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.Sprite;


/**
 * Created by e4klehti on 18.11.2017.
 */
    public class MainPlayer extends GameActor {
    public MainPlayer(int x, int y, int width, int height, Sprite sprite) {
        super(x, y, width, height, sprite);
    }

    public MainPlayer(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void move(Map map) {
        //If its going out of map in X-axis
        if (getX() + getxVelocity() > map.getWidth() || getX() + getxVelocity() < 0 ||
                getX() + getWidth() + getxVelocity() > map.getWidth() || getX() + getxVelocity() + getWidth() < 0) {
            System.out.println("Yli x!");
            setxVelocity(0);
        }

        //If its going out of map in Y-axis
        if (getY() + getyVelocity() > map.getHeight() || getY() + getyVelocity() < 0 ||
                getY() + getHeight() + getyVelocity() > map.getHeight() || getY() + getyVelocity() + getHeight() < 0) {
            setyVelocity(0);
            System.out.println("Yli y!");
        }

        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        getCollider().update(getX(), getY());

    }

    @Override
    public void getInput() {
        getInputlistener().update();
    }

    @Override
    public void onCollision() {
        System.out.println("MainPlayer collison");
    }

    @Override
    public void update() {

    }
}
