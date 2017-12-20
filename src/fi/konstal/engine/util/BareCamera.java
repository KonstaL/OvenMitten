package fi.konstal.engine.util;


import java.io.Serializable;

/**
 * BareCamera is meant to be used as a "dummy camera" when no actual camera is needed.
 *
 * @author Konsta Lehtinen
 * @version 2017-12-20
 */
public class BareCamera implements Camera, Serializable {
    private float xOffset;
    private float yOffset;


    /**
     * Instantiates a new Bare camera.
     */
    public BareCamera() {
        this.xOffset = 0;
        this.yOffset = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getXOffset() {
        return xOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public float getYOffset() {
        return yOffset;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
