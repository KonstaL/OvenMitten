package fi.konstal.example.game2.util;

import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by konka on 28.11.2017.
 */
public class CanvasMap implements Map {
    private SpaceLine[] spaceLines;
    private double width, height;
    private Color fillColor;

    public CanvasMap(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.fillColor = color;
    }


    public void setLineAmount(int amount) {
        spaceLines = new SpaceLine[amount];

        for(int i=0; i < spaceLines.length; i++) {
            spaceLines[i] = new SpaceLine();
        }
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

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

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    class SpaceLine {
        private int x;
        private int y;
        private int lineWidth;
        private int lineHeight;
        private int speed;

        public SpaceLine() {
            this.x = (int) (Math.random() * width);
            this.y = (int) (Math.random()*height);
            this.lineHeight = (int) (Math.random()*10 +10) +1;
            this.lineWidth = (int) (Math.random()*3) +1;
            this.speed = (int) (Math.random() *20) +1;
        }


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

