package fi.konstal.engine.util;

import fi.konstal.engine.GameActor;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;


/**
 * Created by konka on 15.11.2017.
 */
public abstract class KeyboardInput implements EventHandler<KeyEvent>{
    private ArrayList<KeyCode> inputs;
    private GameActor go;
    private boolean restrictMovement;
    private boolean showInputs;


    public KeyboardInput(GameActor go) {
        this.go = go;
        inputs = new ArrayList<>();
        this.restrictMovement = false;
        this.showInputs = false;
    }

    public KeyboardInput() {
        this.go = null;
        inputs = new ArrayList<>();
        this.restrictMovement = false;
        this.showInputs = false;
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            if(!(inputs.contains(event.getCode()))) {
                inputs.add(event.getCode());
            }
        } else if(event.getEventType() == KeyEvent.KEY_RELEASED) {
            inputs.remove(event.getCode());
        }

        if(restrictMovement) {
            restrictedUpdate();
        } else {
            update();
        }

        //print currently pressed keys if true
        if(showInputs) {
            System.out.println(inputs);
        }
    }

    public abstract void update();
    public abstract void restrictedUpdate();


    public GameActor getGameObject() {
        return go;
    }

    public void setRestrictedMovement(Boolean b) {
        restrictMovement = b;
    }
    public void showInputs(Boolean b) { showInputs = b; }

    public ArrayList<KeyCode> getInputs() {
        return inputs;
    }

    public GameActor getGo() {
        return go;
    }

    public void setGo(GameActor go) {
        this.go = go;
    }
}
