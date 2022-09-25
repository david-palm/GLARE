package com.example.glare.events;

public class TouchDownEvent extends TouchEvent {
    public TouchDownEvent(float x, float y){
        super(x, y, Type.TOUCH_DOWN);
    }
}
