package fi.konstal.example.game2.util;

import fi.konstal.engine.map.Map;
import fi.konstal.engine.util.Camera;
import fi.konstal.engine.util.FollowCamera;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by konka on 28.11.2017.
 */
public class NoMap implements Map {
    private SpaceLine[] spaceLines;


    public void setLineAmount(int amount) {
        spaceLines = new SpaceLine[amount];

        for(int i=0; i < spaceLines.length; i++) {
            spaceLines[i] = new SpaceLine();
        }
    }

    @Override
    public void draw(GraphicsContext gc, Camera c) {
        gc.setStroke(Color.WHITE);
        for(SpaceLine sp : spaceLines) {
           gc.strokeRect(sp.x- c.getxOffset(),
                   sp.y - c.getyOffset(),
                   sp.width,
                   sp.height);
           sp.move();
       }
    }

    class SpaceLine {
        private int x;
        private int y;
        private int width;
        private int height;
        private int speed;

        public SpaceLine() {
            this.x = (int) (Math.random() *700);
            this.y = (int) (Math.random()*700);this.width = (int) (Math.random()*2) +1;
            this.height = (int) (Math.random()*10 +10) +1;
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

