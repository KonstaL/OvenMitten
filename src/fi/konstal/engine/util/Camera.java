package fi.konstal.engine.util;

/**
 * Created by konka on 29.11.2017.
 */
public interface Camera {
    void move(float xAmount, float yAmount);
    float getXOffset();
    float getYOffset();
    void setXOffset(float xOffset);
    void setYOffset(float yOffset);
}
