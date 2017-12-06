package fi.konstal.example.game1;

import fi.konstal.engine.*;
import fi.konstal.engine.gameobject.Decoration;
import fi.konstal.engine.gameobject.Enemy;
import fi.konstal.engine.gameobject.GameActor;
import fi.konstal.engine.gameobject.MainPlayer;
import fi.konstal.engine.core.GameLoop;
import fi.konstal.engine.gameobject.collider.Polygon;
import fi.konstal.engine.map.Map;
import fi.konstal.engine.map.tiled.TiledMap;
import fi.konstal.engine.util.*;
import fi.konstal.example.game1.util.KeyInput;
import fi.konstal.example.game1.util.MenuItem;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.List;


/**
 * Created by e4klehti on 14.11.2017.
 */
public class SpaceDestroyer2000 extends GameWindow {
    //Final size for the window
    final int WIDTH = 1280;
    final int HEIGHT = 720;

    @Override
    public void showMainMenu(Stage primaryStage) {

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

        //Add an awesome explosion gif
        Image img = new Image("supernova2.gif");


        //add all the elements to the root Pane
        root.getChildren().add(imgv);
        root.getChildren().add(text);
        root.getChildren().add(line);
        root.getChildren().add(menuBox);


        Scene sc = new Scene(root);
        primaryStage.setScene(sc);


        //link explosion to mouseclick
        sc.addEventFilter(MouseEvent.MOUSE_CLICKED, (event)-> {

            ImageView temp = new ImageView(img);
            temp.setX(event.getX() - temp.getLayoutBounds().getWidth()/2);
            temp.setY(event.getY() - temp.getLayoutBounds().getHeight()/2);
            temp.setRotate(Math.random()*360);
            ColorAdjust ca = new ColorAdjust();
            ca.setBrightness(-0.4);
            ca.setInput(new GaussianBlur());
            temp.setEffect(ca);


            root.getChildren().add(temp);

            //for removing the animation
            Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            //KeyValue kv = root.getChildren().add(temp);
            timeline.getKeyFrames().add(new KeyFrame(Duration.millis(5000)));
            timeline.setOnFinished(a-> {
                root.getChildren().remove(root.getChildren().indexOf(temp));

                //for refreshing the screen
                root.setEffect(new Bloom(0.7));
            });
            timeline.play();
        });



        primaryStage.setResizable(false);
        primaryStage.sizeToScene(); //Prevent extra whitespace caused from setResizable(false);

        primaryStage.show();
    }

    @Override
    public void runGame(Stage primaryStage) {

        Group root = new Group();
        Scene theScene = new Scene( root );
        theScene.setFill(Color.BLACK);


        Canvas canvas = new Canvas(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        root.getChildren().add(canvas);


        Map map = new TiledMap("src/fi/konstal/example/game1/resources/testTMX.tmx",
                "src/fi/konstal/example/game1/resources/");


        //testing
        //============================================
         int ROWS  =   4;
         int PER_ROW    =  6;
         //int OFFSET_X =  18;
         //int OFFSET_Y =  25;
         int WIDTH    = 256;
         int HEIGHT   = 256;
         int CYCLEDURATION = 5;

        Sprite sp = new SpriteAnimation("src/fi/konstal/example/game1/resources/trump_run.png",
                                            ROWS, PER_ROW, WIDTH, HEIGHT, CYCLEDURATION);
        GameActor enemy = new Enemy(400, 400, 300, 300, 40);
        enemy.setSprite(sp);

        Decoration om = new Decoration(500, 1500, 100, 50);
        //============================================




        GameActor ship = new MainPlayer(700,500,50, 50, new SpriteImage("ship.png"), 30);
        ship.setCollider(new Polygon(
                ship.getX(),
                ship.getY(),
                20.0,
                40.0,
                30.0,
                0.0,
                45.0,
                0.0,
                60.0,
                40.0,
                ship.getWidth(),
                40.0,
                ship.getWidth(),
                ship.getHeight(),
                0.0,
                ship.getHeight(),
                0.0,
                40.0));


        FollowCamera fc = new FollowCamera(ship, canvas, (int) map.getWidth(), (int) map.getHeight());

        GameLoop gl = new GameLoop(canvas, map, fc, true, true);

        gl.addGameObject(ship);
        gl.addGameObject(enemy);
        gl.addGameObject(om);


        KeyboardInput input = new KeyInput(ship);
        input.showInputs(true);
        input.setRestrictedMovement(false);

        theScene.setOnKeyPressed(input);
        theScene.setOnKeyReleased(input);


        primaryStage.setScene( theScene );
        primaryStage.show();
        gl.start();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Successfully stopped");
    }


    public void launchThis() {
        super.launch();
    }
}
