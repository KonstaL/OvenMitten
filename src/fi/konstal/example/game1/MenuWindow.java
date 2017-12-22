package fi.konstal.example.game1;

import fi.konstal.engine.core.GameWindow;
import fi.konstal.engine.core.Level;
import fi.konstal.engine.util.GameObservable;
import fi.konstal.engine.util.GameObserver;
import fi.konstal.engine.util.StateMessage;
import fi.konstal.example.game1.util.MenuItem;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;


/**
 * Game1's GameWindow which contains the menu and handles JavaFX UI side.
 *
 * @author Konsta Lehtinen
 * @version 2017 -12-20
 */
public class MenuWindow extends GameWindow implements GameObserver {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    private transient Stage primaryStage;
    private transient Group root;
    private transient VBox pauseMenu;

    private Loop gameLoop;

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;

        List<Pair<String, Runnable>> menuData = Arrays.asList(
                new Pair<String, Runnable>("Start GameWindow", ()-> runGame(primaryStage)),
                new Pair<String, Runnable>("Quit to Desktop", Platform::exit)
        );

        Pane rootPane    = new Pane();
        VBox menuBox = new VBox();

        //Set the background image
        ImageView imgv = new ImageView(new Image("bg.jpg"));
        imgv.setFitWidth(WIDTH);
        imgv.setFitHeight(HEIGHT);



        //Set the title text
        Text text = new Text("O  V  E  N  M  I  T  T  E  N\nE  N  G  I  N  E");
        text.setFont(new Font(40));
        text.setX(WIDTH/2  - text.getBoundsInLocal().getWidth()/2 );
        text.setY(HEIGHT/3.5);
        text.setFill(Color.WHITE);
        text.setEffect(new DropShadow(50, Color.BLACK));



        //Set the line adjacent to menu buttons
        Line line = new Line(WIDTH/3, HEIGHT/3+50, WIDTH/3, HEIGHT/3+50+150);
        line.setStrokeWidth(1);
        line.setStroke(Color.WHITESMOKE);
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(1);



        //Create a verticalBox containing all the menubuttons defined in menuData
        menuBox.setTranslateX(WIDTH/3+5);
        menuBox.setTranslateY(HEIGHT/3+50);
        menuData.forEach(data -> {
                    MenuItem item = new MenuItem(data.getKey());
                    item.setOnAction(data.getValue());

                    menuBox.getChildren().addAll(item);
                });



        //add all the elements to the root Pane
        rootPane.getChildren().add(imgv);
        rootPane.getChildren().add(text);
        rootPane.getChildren().add(line);
        rootPane.getChildren().add(menuBox);


        Scene sc = new Scene(rootPane);

        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene(); //Prevent extra whitespace caused from setResizable(false);

        primaryStage.show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void runGame(Stage primaryStage) {
        Group root = new Group();
        Scene theScene = new Scene( root );


        Canvas canvas = new Canvas(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        root.getChildren().add(canvas);

        Level level = new Level_1();

        this.gameLoop = new Loop(canvas, level, true, true);

        primaryStage.setScene( theScene );
        primaryStage.show();

        gameLoop.addObserver(this);
        gameLoop.start();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GameObservable o, StateMessage arg) {
        if(arg instanceof StateMessage) {
            switch (arg) {
                case LOST:
                    this.gameLoop.stop();
                    this.gameLoop.clear();
                    this.gameLoop = null;
                    showMainMenu(primaryStage);
                    System.out.println("You DIED!");
                    break;
                case WON:
                    this.gameLoop.stop();
                    this.gameLoop.clear();
                    this.gameLoop = null;
                    showMainMenu(primaryStage);
                    System.out.println("You WON!!");
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Successfully stopped");
    }

    /**
     * {@inheritDoc}
     */
    public void launchThis() {
        super.launch();
    }
}
