package fi.konstal.engine;

import javafx.application.Application;
import javafx.stage.Stage;

//TODO: use this abstract class for something useful

/**
 * Created by konka on 21.11.2017.
 */
public abstract class Game extends Application {

    public abstract void showMainMenu(Stage primaryStage);
    public abstract void runGame(Stage primaryStage);
    public abstract void launchThis();

    @Override
    public void start(Stage primaryStage) throws Exception {
        showMainMenu(primaryStage);
    }

    public void load() {
        //TODO: Implement this
        System.out.println("Loading..");
    }
}
