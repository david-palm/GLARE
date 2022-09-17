package com.example.opengl3renderer.events;

public class TouchEvent extends Event{
    protected float x, y;
    public TouchEvent(float x, float y, Type type) {
        super(type);
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}

