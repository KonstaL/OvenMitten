package fi.konstal.example.game2;

import fi.konstal.engine.core.*;

import fi.konstal.engine.util.*;
import fi.konstal.example.game1.util.MenuItem;
import fi.konstal.example.game2.util.*;

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

import java.io.*;
import java.util.Arrays;
import java.util.List;


/**
 * Game2's GameWindow which contains the menu and handles JavaFX UI side.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class MenuWindow extends GameWindow implements GameObserver, Serializable {
    private final int WIDTH = 720;
    private final int HEIGHT = 1000;
    private transient Stage primaryStage;
    private transient Group root;
    private transient VBox pauseMenu;
    
    private Loop gameLoop;

    @Override
    public void showMainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;

        List<Pair<String, Runnable>> menuData = Arrays.asList(
                new Pair<String, Runnable>("Start GameWindow", ()-> runGame(primaryStage)),
                new Pair<String, Runnable>("Load GameWindow", this::load),
                new Pair<String, Runnable>("Quit to Desktop", Platform::exit)
        );

        Pane rootPane  = new Pane();
        VBox menuBox = new VBox();

        //Set the background image
        ImageView imgv = new ImageView(new Image("bg.jpg"));
        imgv.setFitWidth(WIDTH);
        imgv.setFitHeight(HEIGHT);




        //Set the title text
        Text text = new Text("S O F T W A R E   I N V I D E R");
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

    @Override
    public void runGame(Stage primaryStage) {
        root = new Group();
        Scene gameScene = new Scene( root, Color.BLACK );


        Canvas canvas = new Canvas(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        root.getChildren().add(canvas);


        Level level1 = new Level_1();
        Level level2 = new Level_2();
        Level level3 = new Level_3();

        this.gameLoop = new Loop(canvas, level1, true, true);
        this.gameLoop.addLevel(level2);
        this.gameLoop.addLevel(level3);

        primaryStage.setScene( gameScene );
        primaryStage.show();


        gameLoop.addObserver(this);
        gameLoop.start();
    }

    /**
     * Run from save.
     *
     * @param levels the levels
     */
    public void runFromSave(List<Level> levels) {
        this.root = new Group();
        Scene gameScene = new Scene( root, Color.BLACK );


        Canvas canvas = new Canvas(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        root.getChildren().add(canvas);


        this.gameLoop = new Loop(canvas, levels.get(0), true, true);

        for(int i = 1; i < levels.size()-1; i++) {
            gameLoop.addLevel(levels.get(i));
        }


        primaryStage.setScene( gameScene );
        primaryStage.show();


        gameLoop.addObserver(this);
        gameLoop.start();
    }


    /**
     * Show pause screen.
     */
    public void showPauseScreen() {

        List<Pair<String, Runnable>> pauseMenuData = Arrays.asList(
                new Pair<String, Runnable>("Continue", ()-> {
                    root.getChildren().remove(pauseMenu);
                    gameLoop.setRunning(true);
                }),
                new Pair<String, Runnable>("Save", this::save),
                new Pair<String, Runnable>("Quit to main menu", ()-> {
                    gameLoop.stop();
                    gameLoop.clear();
                    gameLoop = null;
                    showMainMenu(primaryStage);
                })
        );


        pauseMenu = new VBox();

        //Create a verticalBox containing all the menubuttons defined in menuData
        pauseMenu.setTranslateX(WIDTH/3+5);
        pauseMenu.setTranslateY(HEIGHT/3+50);
        pauseMenuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());

            pauseMenu.getChildren().addAll(item);
        });

        root.getChildren().add(pauseMenu);
    }




    public void launchThis() {
            super.launch();
        }

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
                case PAUSE:
                    showPauseScreen();
                default:
                    break;
            }
        }
    }

    /**
     * Save.
     */
    public void save() {
        try(FileOutputStream fout = new FileOutputStream("game1_save.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            System.out.println(gameLoop.getLevels());
            oos.writeObject( gameLoop.getLevels());
            System.out.println("Game saved!!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void load() {
        try(FileInputStream fin = new FileInputStream("game1_save.dat");
            ObjectInputStream ois = new ObjectInputStream(fin)) {
            List<Level> levels = (List<Level>) ois.readObject();
            System.out.println("Game loaded!!");
            System.out.println(levels);

            runFromSave(levels);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Successfully stopped");
    }
}

