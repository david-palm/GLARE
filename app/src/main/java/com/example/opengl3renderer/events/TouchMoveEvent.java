package com.example.opengl3renderer.events;

public class TouchMoveEvent extends TouchEvent{
    float prevX;
    float prevY;
    float dX;
    float dY;
    public TouchMoveEvent(float x, float y, float prevX, float prevY) {
        super(x, y, Type.TOUCH_MOVE);
        this.prevX = prevX;
        this.prevY = prevY;
        dX = x - prevX;
        dY = y - prevY;
    }

    public float getPrevX() {
        return prevX;
    }

    public float getPrevY() {
        return prevY;
    }

    public float getDX(){
        return dX;
    }

    public float getDY(){
        return dY;
    }
}
