package com.example.opengl3renderer.events;

public class TouchUpEvent extends TouchEvent {
    public TouchUpEvent(float x, float y){
        super(x, y, Type.TOUCH_UP);
    }
}
