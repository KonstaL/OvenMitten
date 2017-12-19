package fi.konstal.example.game2;

import fi.konstal.engine.*;
import fi.konstal.engine.core.*;

import fi.konstal.engine.util.*;
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
import java.util.Arrays;
import java.util.List;


public class MenuWindow extends GameWindow implements GameObserver {
    final int WIDTH = 720;
    final int HEIGHT = 1000;
    private Stage primaryStage;
    private Loop gameLoop;

    @Override
    public void showMainMenu(Stage primaryStage) {
        this.primaryStage = primaryStage;

        List<Pair<String, Runnable>> menuData = Arrays.asList(
                new Pair<String, Runnable>("Start GameWindow", ()-> runGame(primaryStage)),
                new Pair<String, Runnable>("Load GameWindow", this::load),
                new Pair<String, Runnable>("Quit to Desktop", Platform::exit)
        );

        Pane root    = new Pane();
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
        root.getChildren().add(imgv);
        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(menuBox);


        Scene sc = new Scene(root);
        primaryStage.setScene(sc);




        primaryStage.setResizable(false);
        primaryStage.sizeToScene(); //Prevent extra whitespace caused from setResizable(false);

        primaryStage.show();
    }

    @Override
    public void runGame(Stage primaryStage) {
        Group root = new Group();
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

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Successfully stopped");
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
                default:
                    break;
            }
        }
    }
}

