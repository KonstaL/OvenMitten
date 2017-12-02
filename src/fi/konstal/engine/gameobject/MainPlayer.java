package fi.konstal.engine.gameobject;

import fi.konstal.engine.GameActor;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;

/**
 * Created by e4klehti on 18.11.2017.
 */
    public class MainPlayer extends GameActor {
    public MainPlayer(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
    }
    public MainPlayer(int x, int y, int width, int height) { super(x, y, width, height); }

    @Override
    public void move() {
        setX(getX() + getxVelocity());
        setY(getY() + getyVelocity());
        setBounds(new Rectangle2D(getX(), getY(), getWidth(), getHeight()));
    }

    @Override
    public void getInput() {
        getInputlistener().update();
    }

    @Override
    public void update(Canvas c) {
        if(getX()+getxVelocity() > c.getWidth() || getX()+getxVelocity() < 0 ||
                getX()+getWidth()+getxVelocity() > c.getWidth() || getX()+getxVelocity()+getWidth() < 0) {
            System.out.println("Yli x!");
            setxVelocity(0);
        }

        if( getY()+getyVelocity() > c.getHeight() || getY()+getyVelocity() < 0 ||
                getY()+getHeight()+getyVelocity() > c.getHeight() || getY()+getyVelocity()+getHeight() < 0) {
            setyVelocity(0);
            System.out.println("Yli y!");
        }

        //        getInput();
        //        move();

    }


}
