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

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
