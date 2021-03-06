package fi.konstal.example.game1.util;

import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.util.KeyboardInput;
import fi.konstal.example.game1.Hero;

import javafx.scene.input.KeyCode;

/**
 * The keyboard input class which handles moving
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class KeyInput extends KeyboardInput {

    /**
     * Instantiates a new Key input.
     *
     * @param go the GameObject which it controls
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
        if(getInputs().contains(KeyCode.UP)) {
            getGameObject().setyVelocity(-2);
        }
        if(getInputs().contains(KeyCode.DOWN)) {
            getGameObject().setyVelocity(2);
        }
        if(getInputs().contains(KeyCode.LEFT)) {
            getGameObject().setxVelocity(-2);
        }
        if(getInputs().contains(KeyCode.RIGHT)) {
            getGameObject().setxVelocity(2);
        }

        //If input containsAsset opposite directions
        if (!getInputs().contains(KeyCode.UP) && !getInputs().contains(KeyCode.DOWN) ||
                getInputs().contains(KeyCode.UP) && getInputs().contains(KeyCode.DOWN)) {
            getGameObject().setyVelocity(0);
        }
        if (!getInputs().contains(KeyCode.LEFT) && !getInputs().contains(KeyCode.RIGHT) ||
                getInputs().contains(KeyCode.LEFT) && getInputs().contains(KeyCode.RIGHT)) {
            getGameObject().setxVelocity(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restrictedUpdate() {
        getGameObject().setxVelocity(0);
        getGameObject().setyVelocity(0);

        if (getInputs().size() != 0) {
            KeyCode lastInput = getInputs().get(getInputs().size() - 1);
            switch (lastInput) {
                case UP:
                    getGameObject().setyVelocity(-4);
                    ((Hero)getGameObject()).setDirection(DirectionState.UP);
                    break;
                case DOWN:
                    getGameObject().setyVelocity(4);
                    ((Hero)getGameObject()).setDirection(DirectionState.DOWN);
                    break;
                case LEFT:
                    getGameObject().setxVelocity(-4);
                    ((Hero)getGameObject()).setDirection(DirectionState.LEFT);
                    break;
                case RIGHT:
                    getGameObject().setxVelocity(4);
                    ((Hero)getGameObject()).setDirection(DirectionState.RIGHT);
                    break;
                case SHIFT:
                    ((Hero)getGameObject()).fire();
                    break;
            }
        }
    }
}
