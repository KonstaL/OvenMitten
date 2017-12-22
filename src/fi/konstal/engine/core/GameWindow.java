package fi.konstal.engine.core;

import javafx.application.Application;
import javafx.stage.Stage;


/**
 * The GameWindow is meant as a base class where everything else is launched.
 * Generates the game window and holds the menu
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public abstract class GameWindow extends Application {

    /**
     * Show the main menu
     *
     * @param primaryStage the primary stage
     */
    public abstract void showMainMenu(Stage primaryStage);

    /**
     * Launches the game (Use GameLoop for the game)
     *
     * @param primaryStage the primary stage
     */
    public abstract void runGame(Stage primaryStage);

    /**
     * Launches the Window
     *
     * Used when this application is launched from a separate Main-class.
     * You can launch the application without a separate Main-class by placing the main method here.
     */
    public abstract void launchThis();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        showMainMenu(primaryStage);
    }
}
