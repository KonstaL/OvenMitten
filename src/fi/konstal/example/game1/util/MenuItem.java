package fi.konstal.example.game1.util;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Creates specific looking menu buttons.
 *
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class MenuItem extends Pane {
    private Text text;

    private Effect shadow = new DropShadow(5, 2, 2, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 0);

    /**
     * Instantiates a new Menu item.
     *
     * @param name the text on the button
     */
    public MenuItem(String name) {
        //Make the background for the text
        Polygon bg = new Polygon(
                0, 0,
                200, 0,
                215, 15,
                200, 30,
                0, 30
        );
        bg.setStroke(Color.WHITE);
        bg.setEffect(new GaussianBlur(25));

        //Bind fill property with pressing
        bg.fillProperty().bind(
                Bindings.when(pressedProperty())
                        .then(Color.color(0, 0, 0, 0.80))
                        .otherwise(Color.color(0, 0, 0.1, 0.45))
        );

        //Bind fill property with hovering
        bg.fillProperty().bind(
                Bindings.when(hoverProperty())
                        .then(Color.color(0, 0, 0, 0.60))
                        .otherwise(Color.color(0, 0, 0.1, 0.45))
        );

        //Make the text
        text = new Text(name);
        text.setFont(new Font(14));

        text.setTranslateX(20);
        text.setTranslateY(20);
        text.setFill(Color.WHITE);

        //Bind effectProperty bit hovering
        text.effectProperty().bind(
                Bindings.when(hoverProperty())
                        .then(shadow)
                        .otherwise(blur)
        );

        //Add it to this Pane´s children
        getChildren().addAll(bg, text);
    }

    /**
     * Sets the action of the button
     *
     * @param action the action
     */
    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}

