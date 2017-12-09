package fi.konstal.engine.util;


public class BareCamera implements Camera{
    private float xOffset;
    private float yOffset;


    public BareCamera() {
        this.xOffset = 0;
        this.yOffset = 0;
    }

    @Override
    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
    }

    public float getXOffset() {
        return xOffset;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
