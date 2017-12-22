package fi.konstal.example.game2.util;

import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.util.KeyboardInput;
import fi.konstal.engine.util.StateMessage;
import fi.konstal.example.game2.SpaceShip;
import javafx.scene.input.KeyCode;


/**
 * The type Key input.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class KeyInput extends KeyboardInput {

    /**
     * Instantiates a new Keyboard input used to move a GameObject
     *
     * @param go the gameobject that is controlled
     */
    public KeyInput(GameActor go) {
        super(go);
    }


    /**
     * {@inheritDoc}
     */
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

        //If input containsAsset opposite directions
        if (!getInputs().contains(KeyCode.W) && !getInputs().contains(KeyCode.S) ||
                getInputs().contains(KeyCode.W) && getInputs().contains(KeyCode.S)) {
            getGameObject().setyVelocity(0);
        }
        if (!getInputs().contains(KeyCode.A) && !getInputs().contains(KeyCode.D) ||
                getInputs().contains(KeyCode.A) && getInputs().contains(KeyCode.D)) {
            getGameObject().setxVelocity(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restrictedUpdate() {
        System.out.println("No restricted update set in KeyInput");
    }
}
