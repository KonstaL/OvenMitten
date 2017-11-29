package fi.konstal.example.game2.util;

import fi.konstal.engine.GameActor;
import fi.konstal.engine.util.KeyboardInput;
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
            getGameObject().setyVelocity(-2);
        }
        if(getInputs().contains(KeyCode.S)) {
            getGameObject().setyVelocity(2);
        }
        if(getInputs().contains(KeyCode.A)) {
            getGameObject().setxVelocity(-2);
        }
        if(getInputs().contains(KeyCode.D)) {
            getGameObject().setxVelocity(2);
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
        getGameObject().setxVelocity(0);
        getGameObject().setyVelocity(0);

        if (getInputs().size() != 0) {
            KeyCode lastInput = getInputs().get(getInputs().size() - 1);
            switch (lastInput) {
                case W:
                    getGameObject().setyVelocity(-2);
                    break;
                case S:
                    getGameObject().setyVelocity(2);
                    break;
                case A:
                    getGameObject().setxVelocity(-2);
                    break;
                case D:
                    getGameObject().setxVelocity(2);
                    break;
                default:
                    getGameObject().setxVelocity(0);
                    getGameObject().setyVelocity(0);
                    break;
            }
        }
    }
}
