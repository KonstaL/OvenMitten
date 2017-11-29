package fi.konstal.engine.util;

/**
 * Created by konka on 29.11.2017.
 */
public interface Camera {
    void move(float xAmount, float yAmount);
    float getxOffset();
    float getyOffset();
    void setxOffset(float xOffset);
    void setyOffset(float yOffset);
}
