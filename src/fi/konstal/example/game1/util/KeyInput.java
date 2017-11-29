package fi.konstal.example.game1.util;

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

        //If input contains opposite directions
        if (!getInputs().contains(KeyCode.UP) && !getInputs().contains(KeyCode.DOWN) ||
                getInputs().contains(KeyCode.UP) && getInputs().contains(KeyCode.DOWN)) {
            getGameObject().setyVelocity(0);
        }
        if (!getInputs().contains(KeyCode.LEFT) && !getInputs().contains(KeyCode.RIGHT) ||
                getInputs().contains(KeyCode.LEFT) && getInputs().contains(KeyCode.RIGHT)) {
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
                case UP:
                    getGameObject().setyVelocity(-2);
                    break;
                case DOWN:
                    getGameObject().setyVelocity(2);
                    break;
                case LEFT:
                    getGameObject().setxVelocity(-2);
                    break;
                case RIGHT:
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
