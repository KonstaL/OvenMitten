package fi.konstal.example.game2.util;

import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.util.KeyboardInput;
import fi.konstal.engine.util.StateMessage;
import fi.konstal.example.game2.SpaceShip;
import javafx.scene.input.KeyCode;

/**
 * Created by e4klehti on 22.11.2017.
 */
public class KeyInput extends KeyboardInput {

    public KeyInput(GameActor go) {
        super(go);
    }

    public KeyInput() {}

    @Override
    public void update() {
        //set velocity according to pressed buttons
        if(getInputs().contains(KeyCode.W)) {
            getGameObject().setyVelocity(-4);
        }
        if(getInputs().contains(KeyCode.S)) {
            getGameObject().setyVelocity(4);
        }
        if(getInputs().contains(KeyCode.A)) {
            getGameObject().setxVelocity(-4);
        }
        if(getInputs().contains(KeyCode.D)) {
            getGameObject().setxVelocity(4);
        }
        if (getInputs().contains(KeyCode.SHIFT)) {
            ((SpaceShip)getGameObject()).setShooting(true);
        } else {
            ((SpaceShip)getGameObject()).setShooting(false);
        }

        if (getInputs().contains(KeyCode.ESCAPE)) {
            ((SpaceShip)getGameObject()).notifyObservers(StateMessage.PAUSE);
        }

        //If input contains opposite directions
        if (!getInputs().contains(KeyCode.W) && !getInputs().contains(KeyCode.S) ||
                getInputs().contains(KeyCode.W) && getInputs().contains(KeyCode.S)) {
            getGameObject().setyVelocity(0);
        }
        if (!getInputs().contains(KeyCode.A) && !getInputs().contains(KeyCode.D) ||
                getInputs().contains(KeyCode.A) && getInputs().contains(KeyCode.D)) {
            getGameObject().setxVelocity(0);
        }
    }

    @Override
    public void restrictedUpdate() {
        System.out.println("No restricted update set in KeyInput");
    }
}
