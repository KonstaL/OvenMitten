package fi.konstal.example.game2;

import fi.konstal.engine.*;
import fi.konstal.engine.gameobject.*;
import fi.konstal.engine.core.*;
import fi.konstal.engine.gameobject.collider.*;
import fi.konstal.engine.util.*;
import fi.konstal.example.game2.util.*;

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


public class SoftwareInvader extends Game {
    final int WIDTH = 720;
    final int HEIGHT = 1000;

    @Override
    public void showMainMenu(Stage primaryStage) {
        //Final size for the window


        List<Pair<String, Runnable>> menuData = Arrays.asList(
                new Pair<String, Runnable>("Start Game", ()-> runGame(primaryStage)),
                new Pair<String, Runnable>("Load Game", this::load),
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
        Scene theScene = new Scene( root, Color.BLACK );




        Canvas canvas = new Canvas(primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
        root.getChildren().add(canvas);


        GameActor ship = new MainPlayer(355, 700, 73, 110, new Image("ship.png"));
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


        NoMap map = new NoMap(WIDTH, HEIGHT);
        map.setLineAmount(100);

        BareCamera bc = new BareCamera();

        GameLoop gl = new GameLoop(canvas, map, bc, true, true);
        gl.addGameObject(ship);


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

