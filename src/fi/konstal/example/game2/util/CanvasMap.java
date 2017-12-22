package fi.konstal.example.game2.util;

import fi.konstal.engine.map.Map;
import fi.konstal.engine.camera.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


/**
 * A custom map that contains nothing but lines.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class CanvasMap implements Map {
    private SpaceLine[] spaceLines;
    private double width, height;
    private Color fillColor;

    /**
     * Instantiates a new Canvas map.
     *
     * @param width  the width of the map
     * @param height the height of the map
     * @param color  the color of the lines
     */
    public CanvasMap(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.fillColor = color;
    }


    /**
     * Sets the line amount.
     *
     * @param amount the amount
     */
    public void setLineAmount(int amount) {
        spaceLines = new SpaceLine[amount];

        for(int i=0; i < spaceLines.length; i++) {
            spaceLines[i] = new SpaceLine();
        }
    }

    /**
     * Sets fill color of the lines.
     *
     * @param fillColor the fill color
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(GraphicsContext gc, Camera c) {
        gc.setFill(fillColor);
        for(SpaceLine sp : spaceLines) {
           gc.fillRect(sp.x- c.getXOffset(),
                   sp.y - c.getYOffset(),
                   sp.lineWidth,
                   sp.lineHeight);
           sp.move();
       }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getWidth() {
        return width;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHeight() {
        return height;
    }

    /**
     * A line that is drawn inside CanvasMap.
     *
     * @author Konsta Lehtinen
     * @version 2017-12-22
     */
    class SpaceLine {
        private int x;
        private int y;
        private int lineWidth;
        private int lineHeight;
        private int speed;

        /**
         * Instantiates a new Space line from random values.
         */
        public SpaceLine() {
            this.x = (int) (Math.random() * width);
            this.y = (int) (Math.random()*height);
            this.lineHeight = (int) (Math.random()*10 +10) +1;
            this.lineWidth = (int) (Math.random()*3) +1;
            this.speed = (int) (Math.random() *20) +1;
        }


        /**
         * Moves the spaceline.
         */
        public void move(){
            //move the spaceline
            y += speed;

            //If spaceline is out of bounds, set it back to top
            if(y > 1100) {
                y = -500;
            }
        }
    }
}

